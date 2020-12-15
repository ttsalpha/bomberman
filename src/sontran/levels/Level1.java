package sontran.levels;

import javafx.scene.image.Image;
import sontran.entities.animal.Animal;
import sontran.entities.animal.Ballom;
import sontran.entities.animal.Oneal;
import sontran.graphics.CreateMap;
import sontran.graphics.Sprite;

import static sontran.BombermanGame.*;
import static sontran.control.Menu.bombNumber;
import static sontran.control.Menu.timeNumber;
import static sontran.entities.animal.Bomber.swapKill;
import static sontran.entities.block.Bomb.isBomb;
import static sontran.entities.block.Bomb.powerBomb;
import static sontran.entities.item.SpeedItem.speed;
import static sontran.utility.SoundManager.isSoundDied;
import static sontran.utility.SoundManager.isSoundTitle;

public class Level1 {
    public Level1() {
        enemy.clear();
        block.clear();
        swapKill = 1;
        powerBomb = 0;
        new CreateMap("res/levels/Level1.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        bombNumber = 20;
        isBomb = 0;
        speed = 1;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);

        Animal enemy1 = new Ballom(4, 4, Sprite.ballom_left1.getFxImage());
        Animal enemy2 = new Ballom(9, 9, Sprite.ballom_left1.getFxImage());
        Animal enemy3 = new Ballom(22, 6, Sprite.ballom_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);
        enemy.add(enemy3);

        Animal enemy4 = new Oneal(7, 6, Sprite.oneal_right1.getFxImage());
        Animal enemy5 = new Oneal(13, 8, Sprite.oneal_right1.getFxImage());
        enemy.add(enemy4);
        enemy.add(enemy5);

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}
