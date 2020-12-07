package sontran.entities.block;

import javafx.scene.image.Image;
import sontran.entities.Entity;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;
import static sontran.levels.NextLevel.GoToNextLevel;

public class Portal extends Entity {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (enemy.size() == 0) {
            new Portal(24, 14, Sprite.portal.getFxImage());
            if (player.getX() == this.x && player.getY() == this.y)
                GoToNextLevel();
        }

    }
}