package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

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

    private GraphicsContext gc;
    private Canvas canvas;
    private final List<Entity> entities = new ArrayList<>();
//    private List<Entity> stillObjects = new ArrayList<>();

    private Entity player;
    private int num = 1;
    private final int isMove = 16;


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

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        break;
                    case DOWN:
                        System.out.println("Down");
//                        isMove = player.getY();
                        System.out.println(isMove);
                        down();
                        break;
                    case LEFT:
//                        break;
                    case RIGHT:
                        break;
//                    case SHIFT:
//                        running = false;
//                        break;
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("Bomberman from Son Tran");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                System.out.println("frame " + num);
                num++;
            }
        };
        timer.start();

        createMap();

        player = new Bomber(1, 1, Sprite.player_right_2.getFxImage());
        entities.add(player);
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
//                        stillObjects.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

//    public void down(int x, int y) {
//        int local = ;
//        if (!entities.get((y + 1) * WIDTH + x).equals(Sprite.grass)) {
//            Entity down = new Bomber(y + 1, x, Sprite.player_down_1.getFxImage());
//            player = down;
//        player.setY(player.getY() + 2 * 16);
//        }
//    }

    public void down() {
//        isMove = isMove + 1;
//        if (isMove < 20) {
            player.setY(player.getY() + isMove);
//        } else isMove = 0;
    }
}
