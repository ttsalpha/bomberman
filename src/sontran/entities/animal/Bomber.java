package sontran.entities.animal;

import javafx.scene.image.Image;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;

public class Bomber extends Animal {
    private static int swapKill = 1;
    private static int countKill = 0;

    public Bomber(int isMove, int swap, String direction, int count, int countToRun) {
        super(8, 1, "down", 0, 0);
    }

    public Bomber() {
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    private void killBomber(Animal animal) {
        if (countKill % 16 == 0) {
            if (swapKill == 1) {
                animal.setImg(Sprite.player_dead1.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                animal.setImg(Sprite.player_dead2.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                animal.setImg(Sprite.player_dead3.getFxImage());
                swapKill = 4;
            } else {
                animal.setImg(Sprite.transparent.getFxImage());
            }
        }
    }

    private void checkBombs() {
        if (listKill[player.getX() / 32][player.getY() / 32] == 4)
            player.setLife(false);
    }

    private void checkEnemy() {
        int ax = player.getX() / 32;
        int ay = player.getY() / 32;
        for (Animal animal : enemy) {
            int bx = animal.getX() / 32;
            int by = animal.getY() / 32;
            if (ax == bx && ay == by
                    || ax == bx && ay == by + 1 || ax == bx && ay == by - 1
                    || ay == by && ax == bx + 1 || ay == by && ax == bx - 1) {
                player.life = false;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkBombs();
        checkEnemy();
        countKill++;
        if (!player.life)
            killBomber(player);
    }
}
