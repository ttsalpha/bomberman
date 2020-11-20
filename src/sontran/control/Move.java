package sontran.control;

import sontran.graphics.Sprite;

import static sontran.BombermanGame.player;

public class Move {
    private static final int isMove = 16;
    private static int swap = 1;

    public static void down() {
//        System.out.println("Down");
        if (player.getY() % 16 == 0) {
            if (swap == 1) {
                player.setImg(Sprite.player_down.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                player.setImg(Sprite.player_down_1.getFxImage());
                swap = 3;
            } else {
                player.setImg(Sprite.player_down_2.getFxImage());
                swap = 1;
            }
        }
        player.setY(player.getY() + isMove);
    }

    public static void up() {
//        System.out.println("Up");
        if (player.getY() % 16 == 0) {
            if (swap == 1) {
                player.setImg(Sprite.player_up.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                player.setImg(Sprite.player_up_1.getFxImage());
                swap = 3;
            } else {
                player.setImg(Sprite.player_up_2.getFxImage());
                swap = 1;
            }
        }
        player.setY(player.getY() - isMove);
    }

    public static void left() {
//        System.out.println("Left");
        if (player.getX() % 16 == 0) {
            if (swap == 1) {
                player.setImg(Sprite.player_left.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                player.setImg(Sprite.player_left_1.getFxImage());
                swap = 3;
            } else {
                player.setImg(Sprite.player_left_2.getFxImage());
                swap = 1;
            }
        }
        player.setX(player.getX() - isMove);
    }

    public static void right() {
//        System.out.println("Right");
        if (player.getX() % 16 == 0) {
            if (swap == 1) {
                player.setImg(Sprite.player_right.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                player.setImg(Sprite.player_right_1.getFxImage());
                swap = 3;
            } else {
                player.setImg(Sprite.player_right_2.getFxImage());
                swap = 1;
            }
        }
        player.setX(player.getX() + isMove);
    }
}
