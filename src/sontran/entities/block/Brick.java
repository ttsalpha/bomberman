package sontran.entities.block;

import javafx.scene.image.Image;
import sontran.entities.Entity;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.block;
import static sontran.BombermanGame.listKill;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    private void checkHidden() {
        for (Entity entity : block) {
            if (entity instanceof Brick)
                if (listKill[entity.getX() / 32][entity.getY() / 32] == 4) {
//                    ((Brick) entity).hidden = true;
                    entity.setImg(Sprite.transparent.getFxImage());
                }
        }
    }

    @Override
    public void update() {
        checkHidden();
    }
}