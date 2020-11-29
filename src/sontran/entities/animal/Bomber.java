package sontran.entities.animal;

import javafx.scene.image.Image;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;

public class Bomber extends Animal {
    private static int localX;
    private static int localY;
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
                animal.setImg(Sprite.player_dead1.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                animal.setImg(Sprite.player_dead1.getFxImage());
                swapKill = 4;
            } else {
                animal.setImg(Sprite.transparent.getFxImage());
                swapKill = 1;
            }
        }
    }

    void checkCollide() {
        if (player.getY() % 32 == 0 & player.getX() % 32 == 0) {
            for (Animal animal : enemy) {
                if (player.getX() == animal.getX() && player.getY() == animal.getY()
                        || player.getX() == animal.getX() && player.getY() == animal.getY() + 32
                        || player.getX() == animal.getX() && player.getY() == animal.getY() - 32
                        || player.getY() == animal.getY() && player.getX() == animal.getX() + 32
                        || player.getY() == animal.getY() && player.getX() == animal.getX() - 32) {
                    player.setLife(false);
                    break;
                }
            }
        }
    }

    private void updateMap() {
        if (this.getX() % 32 == 0 && this.getY() % 32 == 0) {
            idObjects[this.getX() / 32][this.getY() / 32] = 5;
            idObjects[localX][localY] = 0;
            localX = this.getX() / 32;
            localY = this.getY() / 32;
        }
    }

    @Override
    public void update() {
        checkCollide();
        countKill++;
        if (!this.life)
            killBomber(this);
    }
}
