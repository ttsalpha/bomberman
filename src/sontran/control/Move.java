package sontran.control;

import sontran.entities.Ballom;
import sontran.entities.Bomber;
import sontran.entities.Entity;
import sontran.graphics.Sprite;

public class Move {
//    public static boolean running = false;

    private static final int isMove = 8;//jump 8 pixel

    private static int swap = 1;        //swap image
    private static String direction;    //direction of player
    private static int count = 0;       //count step of a jump
    public static int countToRun = 0;   //run after 4 frame

    private static int swapEnemy = 1;
    private static String directionEnemy;
    private static int countEnemy = 0;
    public static int countToRunEnemy = 0;

    public static void checkRun(Entity entity) {
        if (entity instanceof Bomber && count > 0) {
            setDirection(direction, entity);
            count--;
        }
        if (entity instanceof Ballom && countEnemy > 0) {
            setDirection(directionEnemy, entity);
            countEnemy--;
        }
    }

    private static void setDirection(String direction, Entity entity) {
        switch (direction) {
            case "down":
                down_step(entity);
                entity.setY(entity.getY() + isMove);
                break;
            case "up":
                up_step(entity);
                entity.setY(entity.getY() - isMove);
                break;
            case "left":
                left_step(entity);
                entity.setX(entity.getX() - isMove);
                break;
            case "right":
                right_step(entity);
                entity.setX(entity.getX() + isMove);
                break;
        }
    }

    public static void down(Entity entity) {
        if (entity.getY() % 32 == 0 && entity.getX() % 32 == 0) {
            if (entity instanceof Bomber && Blocked.block_down(entity)) {
                direction = "down";
                count = 4;
                checkRun(entity);
            }
            if (entity instanceof Ballom && Blocked.block_down(entity)) {
                directionEnemy = "down";
                countEnemy = 4;
                checkRun(entity);
            }
        }
    }

    private static void down_step(Entity entity) {
        if (entity instanceof Bomber && entity.getY() % 8 == 0) {
            if (swap == 1) {
                entity.setImg(Sprite.player_down.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                entity.setImg(Sprite.player_down_1.getFxImage());
                swap = 3;
            } else if (swap == 3) {
                entity.setImg(Sprite.player_down.getFxImage());
                swap = 4;
            } else {
                entity.setImg(Sprite.player_down_2.getFxImage());
                swap = 1;
            }
        }
        if (entity instanceof Ballom && entity.getY() % 8 == 0) {
            if (swapEnemy == 1) {
                entity.setImg(Sprite.ballom_right1.getFxImage());
                swapEnemy = 2;
            } else if (swapEnemy == 2) {
                entity.setImg(Sprite.ballom_right2.getFxImage());
                swapEnemy = 3;
            } else if (swapEnemy == 3) {
                entity.setImg(Sprite.ballom_right3.getFxImage());
                swapEnemy = 4;
            } else {
                entity.setImg(Sprite.ballom_right2.getFxImage());
                swapEnemy = 1;
            }
        }
    }

    public static void up(Entity entity) {
        if (entity.getY() % 32 == 0 && entity.getX() % 32 == 0) {
            if (entity instanceof Bomber && Blocked.block_up(entity)) {
                direction = "up";
                count = 4;
                checkRun(entity);
            }
            if (entity instanceof Ballom && Blocked.block_up(entity)) {
                directionEnemy = "up";
                countEnemy = 4;
                checkRun(entity);
            }
        }
    }

    private static void up_step(Entity entity) {
        if (entity instanceof Bomber && entity.getY() % 8 == 0) {
            if (swap == 1) {
                entity.setImg(Sprite.player_up.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                entity.setImg(Sprite.player_up_1.getFxImage());
                swap = 3;
            } else if (swap == 3) {
                entity.setImg(Sprite.player_up.getFxImage());
                swap = 4;
            } else {
                entity.setImg(Sprite.player_up_2.getFxImage());
                swap = 1;
            }
        }
        if (entity instanceof Ballom && entity.getY() % 8 == 0) {
            if (swapEnemy == 1) {
                entity.setImg(Sprite.ballom_left1.getFxImage());
                swapEnemy = 2;
            } else if (swapEnemy == 2) {
                entity.setImg(Sprite.ballom_left2.getFxImage());
                swapEnemy = 3;
            } else if (swapEnemy == 3) {
                entity.setImg(Sprite.ballom_left3.getFxImage());
                swapEnemy = 4;
            } else {
                entity.setImg(Sprite.ballom_left2.getFxImage());
                swapEnemy = 1;
            }
        }
    }

    public static void left(Entity entity) {
        if (entity.getX() % 32 == 0 && entity.getY() % 32 == 0) {
            if (entity instanceof Bomber && Blocked.block_left(entity)) {
                direction = "left";
                count = 4;
                checkRun(entity);
            }
            if (entity instanceof Ballom && Blocked.block_left(entity)) {
                directionEnemy = "left";
                countEnemy = 4;
                checkRun(entity);
            }
        }
    }

    private static void left_step(Entity entity) {
        if (entity instanceof Bomber && entity.getX() % 8 == 0) {
            if (swap == 1) {
                entity.setImg(Sprite.player_left.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                entity.setImg(Sprite.player_left_1.getFxImage());
                swap = 3;
            } else if (swap == 3) {
                entity.setImg(Sprite.player_left.getFxImage());
                swap = 4;
            } else {
                entity.setImg(Sprite.player_left_2.getFxImage());
                swap = 1;
            }
        }
        if (entity instanceof Ballom && entity.getY() % 8 == 0) {
            if (swapEnemy == 1) {
                entity.setImg(Sprite.ballom_right1.getFxImage());
                swapEnemy = 2;
            } else if (swapEnemy == 2) {
                entity.setImg(Sprite.ballom_right2.getFxImage());
                swapEnemy = 3;
            } else if (swapEnemy == 3) {
                entity.setImg(Sprite.ballom_right3.getFxImage());
                swapEnemy = 4;
            } else {
                entity.setImg(Sprite.ballom_right2.getFxImage());
                swapEnemy = 1;
            }
        }
    }

    public static void right(Entity entity) {
        if (entity.getX() % 32 == 0 && entity.getY() % 32 == 0) {
            if (entity instanceof Bomber && Blocked.block_right(entity)) {
                direction = "right";
                count = 4;
                checkRun(entity);
            }
            if (entity instanceof Ballom && Blocked.block_right(entity)) {
                directionEnemy = "right";
                countEnemy = 4;
                checkRun(entity);
            }
        }
    }

    public static void right_step(Entity entity) {
        if (entity instanceof Bomber && entity.getX() % 8 == 0) {
            if (swap == 1) {
                entity.setImg(Sprite.player_right.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                entity.setImg(Sprite.player_right_1.getFxImage());
                swap = 3;
            } else if (swap == 3) {
                entity.setImg(Sprite.player_right.getFxImage());
                swap = 4;
            } else {
                entity.setImg(Sprite.player_right_2.getFxImage());
                swap = 1;
            }
        }

        if (entity instanceof Ballom && entity.getY() % 8 == 0) {
            if (swapEnemy == 1) {
                entity.setImg(Sprite.ballom_left1.getFxImage());
                swapEnemy = 2;
            } else if (swapEnemy == 2) {
                entity.setImg(Sprite.ballom_left2.getFxImage());
                swapEnemy = 3;
            } else if (swapEnemy == 3) {
                entity.setImg(Sprite.ballom_left3.getFxImage());
                swapEnemy = 4;
            } else {
                entity.setImg(Sprite.ballom_left2.getFxImage());
                swapEnemy = 1;
            }
        }
    }
}
