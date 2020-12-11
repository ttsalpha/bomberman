package sontran.levels;

import javafx.scene.image.Image;
import sontran.entities.animal.Animal;
import sontran.entities.animal.Ballom;
import sontran.entities.animal.Oneal;
import sontran.graphics.CreateMap;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;
import static sontran.BombermanGame.enemy;
import static sontran.control.Menu.bombNumber;
import static sontran.control.Menu.timeNumber;
import static sontran.entities.animal.Bomber.swapKill;
import static sontran.entities.item.SpeedItem.speed;
import static sontran.utility.SoundManager.isSoundDied;
import static sontran.utility.SoundManager.isSoundTitle;

public class Level2 {
    public Level2() {
        enemy.clear();
        block.clear();
        swapKill = 1;
        new CreateMap("res/levels/Level2.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        speed = 1;
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        bombNumber = 20;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);

        Animal enemy1 = new Ballom(5, 5, Sprite.ballom_left1.getFxImage());
        Animal enemy2 = new Ballom(11, 9, Sprite.ballom_left1.getFxImage());
        Animal enemy3 = new Ballom(21, 7, Sprite.ballom_left1.getFxImage());
        Animal enemy4 = new Ballom(1, 9, Sprite.ballom_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);
        enemy.add(enemy3);
        enemy.add(enemy4);

        Animal enemy5 = new Oneal(7, 5, Sprite.oneal_right1.getFxImage());
        Animal enemy6 = new Oneal(19, 7, Sprite.oneal_right1.getFxImage());
        enemy.add(enemy5);
        enemy.add(enemy6);

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}