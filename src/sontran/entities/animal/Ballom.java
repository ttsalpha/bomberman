package sontran.entities.animal;

import javafx.scene.image.Image;
import sontran.control.Move;
import sontran.graphics.Sprite;

import java.util.Random;

import static sontran.BombermanGame.enemy;
import static sontran.BombermanGame.listKill;

public class Ballom extends Animal {
    private static int swapKill = 1;
    private static int countKill = 0;

    public Ballom(int isMove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Ballom() {
    }

    private void killBallom(Animal animal) {
        if (countKill % 16 == 0) {
            if (swapKill == 1) {
                animal.setImg(Sprite.mob_dead1.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                animal.setImg(Sprite.mob_dead2.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                animal.setImg(Sprite.mob_dead3.getFxImage());
                swapKill = 4;
            } else {
                animal.setLife(false);
                enemy.remove(animal);
                swapKill = 1;
            }
        }
    }

    private void kill() {
        for (Animal animal : enemy) {
            if (listKill[animal.getX() / 32][animal.getY() / 32] == 4) {
                animal.setLife(false);
            }
        }
    }

    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        kill();
        countKill++;
        for (Animal animal : enemy) {
            if (animal instanceof Ballom && !animal.life)
                killBallom(animal);
        }

        if (this.y % 16 == 0 && this.x % 16 == 0) {
            Random random = new Random();
            int dir = random.nextInt(4);
            switch (dir) {
                case 0:
                    Move.down(this);
                    break;
                case 1:
                    Move.up(this);
                    break;
                case 2:
                    Move.left(this);
                    break;
                case 3:
                    Move.right(this);
                    break;
            }
        }
    }
}