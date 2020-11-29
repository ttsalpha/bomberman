package sontran;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import sontran.control.Move;
import sontran.entities.Entity;
import sontran.entities.animal.Animal;
import sontran.entities.animal.Ballom;
import sontran.entities.animal.Bomber;
import sontran.entities.block.*;
import sontran.entities.item.PowerUpBombs;
import sontran.graphics.Sprite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;

    public static final List<Entity> block = new ArrayList<>();
    public static List<Animal> enemy = new ArrayList<>();
    public static int[][] idObjects;
    public static int[][] listKill;
    public static Animal player;

    private static final List<Entity> background = new ArrayList<>();
    private GraphicsContext gc;
    private Canvas canvas;

    private int frame = 1;
    private long lastTime;

    private static Stage mainStage = null;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    Move.up(player);
                    break;
                case DOWN:
                    Move.down(player);
                    break;
                case LEFT:
                    Move.left(player);
                    break;
                case RIGHT:
                    Move.right(player);
                    break;
                case SPACE:
                    Bomb.putBomb();
                    break;
//                    case SHIFT:
//                        running = false;
//                        break;
            }
        });

        stage.setScene(scene);
        stage.setTitle("Bomberman from Son Tran");
        mainStage = stage;
        mainStage.show();

        lastTime = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                time();
            }
        };
        timer.start();

        createMap();

        player = new Bomber(1, 1, Sprite.player_right_2.getFxImage());
        player.setLife(true);

        createBackground();
        addCreature();
    }


    public void addCreature() {
        Animal enemy1 = new Ballom(4, 4, Sprite.ballom_left1.getFxImage());
        Animal enemy2 = new Ballom(9, 9, Sprite.ballom_left1.getFxImage());
        Animal enemy3 = new Ballom(22, 6, Sprite.ballom_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);
        enemy.add(enemy3);
        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }

    public void createBackground() {
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++) {
                Entity obj = new Grass(i, j, Sprite.grass.getFxImage());
                background.add(obj);
            }

    }

    public void createMap() {
        // levels/Level0.txt
        // ../level1.txt
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File("res/levels/Level1.txt");
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();

            StringTokenizer tokens = new StringTokenizer(line);
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                idObjects = new int[_width][_height];
                listKill = new int[_width][_height];
                for (int i = 0; i < _height; ++i) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < _width; j++) {
                        int s = Integer.parseInt(tokenTile.nextToken());
                        idObjects[j][i] = s;
                        Entity entity;
                        switch (s) {
                            case 1:
                                entity = new Portal(j, i, Sprite.brick.getFxImage());
                                break;
                            case 2:
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case 3:
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            case 6:
                                entity = new PowerUpBombs(j, i, Sprite.brick.getFxImage());
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                        }
                        //0 grass	1 portal	    2 wall
                        //3 brick	4  bomb/kill	5 enemy
                        //6 power up bombs
                        //7 power up flames
                        //8 power up speed
                        block.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        block.forEach(Entity::update);
        enemy.forEach(Entity::update);

        player.setCountToRun(player.getCountToRun() + 1);
        if (player.getCountToRun() == 4) {
            Move.checkRun(player);
            player.setCountToRun(0);
        }

        for (Animal a : enemy) {
            a.setCountToRun(a.getCountToRun() + 1);
            if (a.getCountToRun() == 8) {
                Move.checkRun(a);
                a.setCountToRun(0);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        background.forEach(g -> g.render(gc));
        block.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
        player.render(gc);
    }

    public void time() {
//        final double ns = 1000000000.0 / 60.0; //nanosecond, 60 frames per second
//        canvas.requestFocus();
        frame++;

        long now = System.currentTimeMillis();
        if (now - lastTime > 1000) {
            lastTime = System.currentTimeMillis();
            mainStage.setTitle("Bomberman from Son Tran | " + frame + " fps");
            frame = 0;
        }
    }
}
