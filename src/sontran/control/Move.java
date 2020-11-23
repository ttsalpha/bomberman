package sontran.control;

import sontran.graphics.Sprite;

import static sontran.BombermanGame.player;

public class Move {
    public static boolean running = false;
    private static final int isMove = 8;
    private static int swap = 1;
    public static String direction;
    private static int count = 0;
    public static int countToRun = 0;

    public static void checkRun() {
        if (count > 0) {
            switch (direction) {
                case "down":
                    down_step();
                    player.setY(player.getY() + isMove);
                    break;
                case "up":
                    up_step();
                    player.setY(player.getY() - isMove);
                    break;
                case "left":
                    left_step();
                    player.setX(player.getX() - isMove);
                    break;
                case "right":
                    right_step();
                    player.setX(player.getX() + isMove);
                    break;
            }
            count--;
        }
    }

    public static void down() {
        if (player.getY() % 32 == 0) {
            if (Blocked.block_down()) {
                direction = "down";
                count = 4;
                checkRun();
            }
        }
    }

    private static void down_step() {
        if (player.getY() % 8 == 0) {
            if (swap == 1) {
                player.setImg(Sprite.player_down.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                player.setImg(Sprite.player_down_1.getFxImage());
                swap = 3;
            } else if (swap == 3) {
                player.setImg(Sprite.player_down.getFxImage());
                swap = 4;
            } else {
                player.setImg(Sprite.player_down_2.getFxImage());
                swap = 1;
            }
        }
    }

    public static void up() {
        if (player.getY() % 32 == 0) {
            if (Blocked.block_up()) {
                direction = "up";
                count = 4;
                checkRun();
            }
        }
    }

    private static void up_step() {
        if (player.getY() % 8 == 0) {
            if (swap == 1) {
                player.setImg(Sprite.player_up.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                player.setImg(Sprite.player_up_1.getFxImage());
                swap = 3;
            } else if (swap == 3) {
                player.setImg(Sprite.player_up.getFxImage());
                swap = 4;
            } else {
                player.setImg(Sprite.player_up_2.getFxImage());
                swap = 1;
            }
        }
    }

    public static void left() {
        if (player.getX() % 32 == 0) {
            if (Blocked.block_left()) {
                direction = "left";
                count = 4;
                checkRun();
            }
        }
    }

    private static void left_step() {
        if (player.getX() % 8 == 0) {
            if (swap == 1) {
                player.setImg(Sprite.player_left.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                player.setImg(Sprite.player_left_1.getFxImage());
                swap = 3;
            } else if (swap == 3) {
                player.setImg(Sprite.player_left.getFxImage());
                swap = 4;
            } else {
                player.setImg(Sprite.player_left_2.getFxImage());
                swap = 1;
            }
        }
    }

    public static void right() {
        if (player.getX() % 32 == 0) {
            if (Blocked.block_right()) {
                direction = "right";
                count = 4;
                checkRun();
            }
        }
    }

    public static void right_step() {
        if (Blocked.block_right()) {
            if (player.getX() % 8 == 0) {
                if (swap == 1) {
                    player.setImg(Sprite.player_right.getFxImage());
                    swap = 2;
                } else if (swap == 2) {
                    player.setImg(Sprite.player_right_1.getFxImage());
                    swap = 3;
                } else if (swap == 3) {
                    player.setImg(Sprite.player_right.getFxImage());
                    swap = 4;
                } else {
                    player.setImg(Sprite.player_right_2.getFxImage());
                    swap = 1;
                }
            }
        }
    }
}
