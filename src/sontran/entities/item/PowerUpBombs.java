package sontran.entities.item;

import javafx.scene.image.Image;
import sontran.entities.Entity;
import sontran.entities.block.Bomb;
import sontran.graphics.Sprite;


import static sontran.BombermanGame.*;

public class PowerUpBombs extends Entity {


    public PowerUpBombs(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        for (Entity entity : block)
            if (entity instanceof PowerUpBombs)
                if (listKill[entity.getX() / 32][entity.getY() / 32] == 4) {
                    entity.setImg(Sprite.powerup_bombs.getFxImage());
                }
        if (player.getX() == this.x && player.getY() == this.y) {
            this.setImg(Sprite.transparent.getFxImage());
            Bomb.powerBomb += 2;
        }
    }
}
