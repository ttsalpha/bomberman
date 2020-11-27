package sontran.control;

import sontran.entities.Entity;
import sontran.entities.block.Grass;

import static sontran.BombermanGame.*;

public class Blocked {
    public static boolean allows_movement = false;
    private static int local;

    public static boolean checkBlocks(int local) {
//        allows_movement = local == 0;
        allows_movement = entities.get(local) instanceof Grass;
        return allows_movement;
    }

    public static boolean block_down(Entity entity) {
        local = (entity.getY() / 32 + 1) * WIDTH + entity.getX() / 32;
        return checkBlocks(local);
    }

    public static boolean block_up(Entity entity) {
        local = (entity.getY() / 32 - 1) * WIDTH + entity.getX() / 32;
        return checkBlocks(local);
    }

    public static boolean block_left(Entity entity) {
        local = entity.getY() / 32 * WIDTH + entity.getX() / 32 - 1;
        return checkBlocks(local);
    }

    public static boolean block_right(Entity entity) {
        local = (entity.getY()) / 32 * WIDTH + entity.getX() / 32 + 1;
        return checkBlocks(local);
    }
}