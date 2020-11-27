package sontran;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import sontran.control.Move;
import sontran.entities.*;
import sontran.entities.animal.Animal;
import sontran.entities.animal.Ballom;
import sontran.entities.animal.Bomber;
import sontran.entities.block.Brick;
import sontran.entities.block.Grass;
import sontran.entities.block.Portal;
import sontran.entities.block.Wall;
import sontran.graphics.Sprite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    public static final List<Entity> entities = new ArrayList<>();
    public static int[][] idObjects = new int[WIDTH][HEIGHT];
    public static Animal player;
    public static List<Animal> enemy = new ArrayList<>();

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
        entities.add(player);

        addCreature();
    }


    public void addCreature() {
        Animal enemy1 = new Ballom(4, 4, Sprite.ballom_left1.getFxImage());
        Animal enemy2 = new Ballom(9, 9, Sprite.ballom_left1.getFxImage());
        Animal enemy3 = new Ballom(22, 6, Sprite.ballom_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);
        enemy.add(enemy3);
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
            int _level = Integer.parseInt(tokens.nextToken());
            int _height = Integer.parseInt(tokens.nextToken());
            int _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                for (int i = 0; i < _height; ++i) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < _width; j++) {
                        int s = Integer.parseInt(tokenTile.nextToken());
                        idObjects[j][i] = s;
                        Entity entity;
                        switch (s) {
                            case 1:
                                entity = new Portal(j, i, Sprite.portal.getFxImage());
                                break;
                            case 2:
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case 3:
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage());

                        }
                        entities.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
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
        entities.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
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
