package sontran.control;

import sontran.entities.animal.Ballom;
import sontran.entities.animal.Bomber;
import sontran.entities.animal.Animal;
import sontran.graphics.Sprite;

public class Move {
//    public static boolean running = false;

//    private static final int isMove = 8;//jump 8 pixel - !fix
//    private static int swap = 1;        //swap image
//    private static String direction;    //direction of player
//    private static int count = 0;       //count step of a jump
//    public static int countToRun = 0;   //run after 4 frame


    public static void checkRun(Animal animal) {
        if (animal instanceof Bomber && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 8);
            animal.setCount(animal.getCount() - 1);
        }
        if (animal instanceof Ballom && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
    }

    private static void setDirection(String direction, Animal animal, int isMove) {
        switch (direction) {
            case "down":
                down_step(animal);
                animal.setY(animal.getY() + isMove);
                break;
            case "up":
                up_step(animal);
                animal.setY(animal.getY() - isMove);
                break;
            case "left":
                left_step(animal);
                animal.setX(animal.getX() - isMove);
                break;
            case "right":
                right_step(animal);
                animal.setX(animal.getX() + isMove);
                break;
        }
    }

    public static void down(Animal animal) {
        if (animal.getY() % 32 == 0 && animal.getX() % 32 == 0) {
            if (animal instanceof Bomber && Blocked.block_down(animal)) {
                animal.setDirection("down");
                animal.setCount(4);
                checkRun(animal);
            }
            if (animal instanceof Ballom && Blocked.block_down(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }

    private static void down_step(Animal animal) {
        if (animal instanceof Bomber && animal.getY() % 8 == 0) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.player_down.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.player_down_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.player_down.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.player_down_2.getFxImage());
                animal.setSwap(1);
            }
        }
        if (animal instanceof Ballom && animal.getY() % 8 == 0) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.ballom_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.ballom_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.ballom_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.ballom_right2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    public static void up(Animal animal) {
        if (animal.getY() % 32 == 0 && animal.getX() % 32 == 0) {
            if (animal instanceof Bomber && Blocked.block_up(animal)) {
                animal.setDirection("up");
                animal.setCount(4);
                checkRun(animal);
            }
            if (animal instanceof Ballom && Blocked.block_up(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }

    private static void up_step(Animal animal) {
        if (animal instanceof Bomber && animal.getY() % 8 == 0) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.player_up.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.player_up_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.player_up.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.player_up_2.getFxImage());
                animal.setSwap(1);
            }
        }
        if (animal instanceof Ballom && animal.getY() % 8 == 0) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.ballom_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.ballom_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.ballom_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.ballom_left2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    public static void left(Animal animal) {
        if (animal.getX() % 32 == 0 && animal.getY() % 32 == 0) {
            if (animal instanceof Bomber && Blocked.block_left(animal)) {
                animal.setDirection("left");
                animal.setCount(4);
                checkRun(animal);
            }
            if (animal instanceof Ballom && Blocked.block_left(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }

    private static void left_step(Animal animal) {
        if (animal instanceof Bomber && animal.getX() % 8 == 0) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.player_left.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.player_left_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.player_left.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.player_left_2.getFxImage());
                animal.setSwap(1);
            }
        }
        if (animal instanceof Ballom && animal.getY() % 8 == 0) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.ballom_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.ballom_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.ballom_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.ballom_right2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    public static void right(Animal animal) {
        if (animal.getX() % 32 == 0 && animal.getY() % 32 == 0) {
            if (animal instanceof Bomber && Blocked.block_right(animal)) {
                animal.setDirection("right");
                animal.setCount(4);
                checkRun(animal);
            }
            if (animal instanceof Ballom && Blocked.block_right(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }

    public static void right_step(Animal animal) {
        if (animal instanceof Bomber && animal.getX() % 8 == 0) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.player_right.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.player_right_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.player_right.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.player_right_2.getFxImage());
                animal.setSwap(1);
            }
        }

        if (animal instanceof Ballom && animal.getY() % 8 == 0) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.ballom_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.ballom_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.ballom_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.ballom_left2.getFxImage());
                animal.setSwap(1);
            }
        }
    }
}
