package sontran.entities;

import javafx.scene.image.Image;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;
import static sontran.BombermanGame.player;

public class Bomb extends Entity {
    private static long timeBomb;
    private static Entity bomb;
    private static int swap;

    public static boolean isBomb = false;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void putBomb() {
        if (!isBomb) {
            isBomb = true;
            timeBomb = System.currentTimeMillis();
            int x = player.getX() / 32;
            int y = player.getY() / 32;
            x = Math.round(x);
            y = Math.round(y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            entities.add(bomb);
            idObjects[player.getY() / 32][player.getX() / 32] = 4;
        }
    }

    public static void activeBomb() {
        if (System.currentTimeMillis() - timeBomb == 200) {
            if (swap == 1) {
                bomb.setImg(Sprite.bomb.getFxImage());
                swap = 2;
            } else if (swap == 2) {
                bomb.setImg(Sprite.bomb_1.getFxImage());
                swap = 3;
            } else if (swap == 3) {
                bomb.setImg(Sprite.bomb.getFxImage());
                swap = 4;
            } else {
                bomb.setImg(Sprite.bomb_2.getFxImage());
                swap = 1;
            }
        }
    }

    public static void explosion() {
        if (System.currentTimeMillis() - timeBomb < 1000) {
            if (System.currentTimeMillis() - timeBomb == 200) {
                if (swap == 1) {
                    bomb.setImg(Sprite.explosion_horizontal.getFxImage());
                    swap = 2;
                } else if (swap == 2) {
                    bomb.setImg(Sprite.explosion_horizontal1.getFxImage());
                    swap = 3;
                } else if (swap == 3) {
                    bomb.setImg(Sprite.explosion_horizontal2.getFxImage());
                    swap = 1;
                }
                timeBomb -= 200;
            }
        } else {
            entities.remove(bomb);
            isBomb = false;
        }
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - timeBomb < 2000) {
            activeBomb();
            timeBomb -= 100;
        } else {
            timeBomb = System.currentTimeMillis();
            explosion();
        }
    }
}
