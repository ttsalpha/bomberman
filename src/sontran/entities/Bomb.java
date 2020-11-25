package sontran.entities;

import javafx.scene.image.Image;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;
import static sontran.BombermanGame.player;

public class Bomb extends Entity {
    private static long timeBomb;
    private static long timeTmp;
    private static Entity bomb;
    private static int swapActive = 1;
    private static int swapExplosion = 1;
    private static int powerBomb = 0;

    public static int isBomb = 0;   //0 no bomb /1 had bomb /2 explosion

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void putBomb() {
        if (isBomb == 0) {
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            powerBomb = 1;
            timeTmp = timeBomb;
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
        if (swapActive == 1) {
            bomb.setImg(Sprite.bomb.getFxImage());
            swapActive = 2;
        } else if (swapActive == 2) {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            swapActive = 3;
        } else if (swapActive == 3) {
            bomb.setImg(Sprite.bomb_2.getFxImage());
            swapActive = 4;
        } else {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            swapActive = 1;
        }
    }

    public static void explosion() {
        if (swapExplosion == 1) {
            bomb.setImg(Sprite.explosion_horizontal.getFxImage());
            swapExplosion = 2;
        } else if (swapExplosion == 2) {
            bomb.setImg(Sprite.explosion_horizontal1.getFxImage());
            swapExplosion = 3;
        } else if (swapExplosion == 3) {
            bomb.setImg(Sprite.explosion_horizontal2.getFxImage());
            swapExplosion = 1;
        }
    }

    private static void checkActive() {
        if (isBomb == 1) {
            if (System.currentTimeMillis() - timeBomb < 2000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    activeBomb();
                    timeTmp += 100;
                }
            } else {
                isBomb = 2;
                timeBomb = System.currentTimeMillis();
                timeTmp = timeBomb;
            }
        }
    }

    private static void checkExplosion() {
        if (isBomb == 2)
            if (System.currentTimeMillis() - timeBomb < 1000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    explosion();
                    timeTmp += 100;
                }
            } else {
                isBomb = 0;
                entities.remove(bomb);
            }
    }

    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}
