package sontran.control;

import javafx.scene.image.Image;
import sontran.entities.Bomb;
import sontran.entities.Entity;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;

public class Explosion extends Entity {
    private static int timeBomb = 2000;

    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static void putBomb() {
        int x = player.getX() / 36 + 1;
        int y = player.getY() / 36 + 1;
        x = Math.round(x);
        y = Math.round(y);
        Entity bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
        entities.add(bomb);
        idObjects[player.getY() / 36][player.getX() / 36] = 4;
    }

    @Override
    public void update() {
        if (timeBomb > 0)
            timeBomb--;
    }
}
