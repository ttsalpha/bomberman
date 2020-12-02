package sontran.entities.item;

import javafx.scene.image.Image;
import sontran.entities.Entity;

public abstract class Items extends Entity {

    protected boolean received = false;

    public Items(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Items() {
    }

    public Items(boolean received) {
    }

    @Override
    public void update() {

    }
}
