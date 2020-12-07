package sontran.levels;

import static sontran.BombermanGame._level;

public class NextLevel {

    public static void GoToNextLevel() {
        if (_level == 1)
            new Level2();
    }
}
