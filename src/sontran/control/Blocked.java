package sontran.control;

import sontran.entities.Entity;

import static sontran.BombermanGame.*;

public class Blocked {

    public static boolean block_down(Entity entity) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 + 1] == 0;
    }

    public static boolean block_up(Entity entity) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 - 1] == 0;
    }

    public static boolean block_left(Entity entity) {
        return idObjects[entity.getX() / 32 - 1][entity.getY() / 32] == 0;
    }

    public static boolean block_right(Entity entity) {
        return idObjects[entity.getX() / 32 + 1][entity.getY() / 32] == 0;
    }

    public static boolean block_down_bomb(Entity entity, int power) {
//        System.out.println("bock: " + entity.getX() / 32 + " " + (entity.getY() / 32 + 1));
//        System.out.println("id Ogj: " + idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power]);
        return idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 0
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 3;
    }

    public static boolean block_up_bomb(Entity entity, int power) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 0
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 3;
    }

    public static boolean block_left_bomb(Entity entity, int power) {
        return idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 0
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 3;
    }

    public static boolean block_right_bomb(Entity entity, int power) {
        return idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 0
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 3;
    }
}