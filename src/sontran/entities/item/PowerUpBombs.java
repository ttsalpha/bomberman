package sontran.entities.item;

import javafx.scene.image.Image;
import sontran.entities.Entity;
import sontran.entities.block.Bomb;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;

public class PowerUpBombs extends Items {

    public PowerUpBombs(int x, int y, Image img) {
        super(x, y, img);
    }

    public PowerUpBombs(boolean received) {
        super(received);
    }

    public PowerUpBombs() {
    }

    @Override
    public void update() {
        for (Entity entity : block)
            if (entity instanceof PowerUpBombs && !this.received)
                if (listKill[entity.getX() / 32][entity.getY() / 32] == 4)
                    entity.setImg(Sprite.powerup_bombs.getFxImage());

        if (!this.received)
            if (player.getX() == this.x && player.getY() == this.y) {
                this.setImg(Sprite.transparent.getFxImage());
                this.received = true;
                Bomb.powerBomb += 2;
            }
    }
}
