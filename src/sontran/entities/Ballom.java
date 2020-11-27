package sontran.entities;

import javafx.scene.image.Image;
import sontran.control.Move;

import java.util.Random;

import static sontran.BombermanGame.enemy;

public class Ballom extends Entity {

    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.y % 16 == 0 && this.x % 16 == 0) {
            Random random = new Random();
            int dir = random.nextInt(4);
            switch (dir) {
                case 0:
                    Move.down(enemy.get(0));
                    break;
                case 1:
                    Move.up(enemy.get(0));
                    break;
                case 2:
                    Move.left(enemy.get(0));
                    break;
                case 3:
                    Move.right(enemy.get(0));
                    break;
            }
        }
    }
}