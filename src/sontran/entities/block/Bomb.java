package sontran.entities.block;

import javafx.scene.image.Image;
import sontran.control.Blocked;
import sontran.entities.Entity;
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
    private static Entity edge_down;
    private static Entity edge_up;
    private static Entity edge_left;
    private static Entity edge_right;

    public static int isBomb = 0;   //0 no bomb /1 had bomb /2 explosion

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void putBomb() {
        if (isBomb == 0) {
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            int x = player.getX() / 32;
            int y = player.getY() / 32;
            x = Math.round(x);
            y = Math.round(y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            block.add(bomb);
            idObjects[player.getX() / 32][player.getY() / 32] = 4;
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

    public static void createEdge() {
        edge_down = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + 1 + powerBomb,
                Sprite.transparent.getFxImage());
        block.add(edge_down);
        edge_up = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1 - powerBomb,
                Sprite.transparent.getFxImage());
        block.add(edge_up);
        edge_left = new Bomb(bomb.getX() / 32 - 1 - powerBomb, bomb.getY() / 32,
                Sprite.transparent.getFxImage());
        block.add(edge_left);
        edge_right = new Bomb(bomb.getX() / 32 + 1 + powerBomb, bomb.getY() / 32,
                Sprite.transparent.getFxImage());
        block.add(edge_right);
    }

    public static void explosionCenter() {
        if (swapExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            if (Blocked.block_down_bomb(bomb, powerBomb))
                edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
            if (Blocked.block_up_bomb(bomb, powerBomb))
                edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
            if (Blocked.block_left_bomb(bomb, powerBomb))
                edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
            if (Blocked.block_right_bomb(bomb, powerBomb))
                edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
            swapExplosion = 2;
        } else if (swapExplosion == 2) {
            bomb.setImg(Sprite.bomb_exploded1.getFxImage());
            if (Blocked.block_down_bomb(bomb, powerBomb))
                edge_down.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            if (Blocked.block_up_bomb(bomb, powerBomb))
                edge_up.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            if (Blocked.block_left_bomb(bomb, powerBomb))
                edge_left.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            if (Blocked.block_right_bomb(bomb, powerBomb))
                edge_right.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            swapExplosion = 3;
        } else if (swapExplosion == 3) {
            bomb.setImg(Sprite.bomb_exploded2.getFxImage());
            if (Blocked.block_down_bomb(bomb, powerBomb))
                edge_down.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            if (Blocked.block_up_bomb(bomb, powerBomb))
                edge_up.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            if (Blocked.block_left_bomb(bomb, powerBomb))
                edge_left.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            if (Blocked.block_right_bomb(bomb, powerBomb))
                edge_right.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
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
                    createEdge();
                    explosionCenter();
                    timeTmp += 100;
                }
            } else {
                isBomb = 0;
                idObjects[bomb.getX() / 32][bomb.getY() / 32] = 0;
                bomb.setImg(Sprite.grass.getFxImage());
                if (Blocked.block_down_bomb(bomb, powerBomb)) {
                    edge_down.setImg(Sprite.grass.getFxImage());
                    idObjects[edge_down.getX() / 32][edge_down.getY() / 32] = 0;
                }
                if (Blocked.block_up_bomb(bomb, powerBomb)) {
                    edge_up.setImg(Sprite.grass.getFxImage());
                    idObjects[edge_up.getX() / 32][edge_up.getY() / 32] = 0;
                }
                if (Blocked.block_left_bomb(bomb, powerBomb)) {
                    edge_left.setImg(Sprite.grass.getFxImage());
                    idObjects[edge_left.getX() / 32][edge_left.getY() / 32] = 0;
                }
                if (Blocked.block_right_bomb(bomb, powerBomb)) {
                    edge_right.setImg(Sprite.grass.getFxImage());
                    idObjects[edge_right.getX() / 32][edge_right.getY() / 32] = 0;
                }
            }
    }

    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}
