package sontran.control;

import sontran.entities.Grass;

import static sontran.BombermanGame.*;

public class Blocked {
    public static boolean allows_movement = false;
    private static int local;

    public static boolean checkBlocks(int local) {
        allows_movement = entities.get(local) instanceof Grass;
        return allows_movement;
    }

    public static boolean block_down() {
        local = (player.getY() / 32 + 1) * WIDTH + player.getX() / 32;
//        System.out.println(entities.get(local).getX() / 32 + " " + entities.get(local).getY() / 32);
        return checkBlocks(local);
    }

    public static boolean block_up() {
        local = (player.getY() / 32) * WIDTH + player.getX() / 32;
        return checkBlocks(local);
    }

    public static boolean block_left() {
        local = (player.getY()) / 32 * WIDTH + player.getX() / 32;
        return checkBlocks(local);
    }

    public static boolean block_right() {
        local = (player.getY()) / 32 * WIDTH + player.getX() / 32 + 1;
        return checkBlocks(local);
    }
}