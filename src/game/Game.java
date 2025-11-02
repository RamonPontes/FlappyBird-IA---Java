package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    //  Screen dimensions
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;

    // Bird configuration
    private static final double BIRD_WIDTH = 34;
    private static final double BIRD_HEIGHT = 24;
    private static final double DEFAULT_VELOCITY = 0;
    private static final double GRAVITY = 0.5;
    private static final double JUMP_STRENGTH = -10;

    private static final int POPULATION_SIZE = 50;

    private List<Bird> birds;
    private Thread gameLoop;
    private GameScreen gameScreen;

    public Game() {
        birds = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            birds.add(new Bird(100, SCREEN_HEIGHT / 2, BIRD_WIDTH, BIRD_HEIGHT, DEFAULT_VELOCITY, GRAVITY, JUMP_STRENGTH));
        }

        gameScreen = new GameScreen(SCREEN_WIDTH, SCREEN_HEIGHT, birds);

        gameLoop().start();
    }

    public Thread gameLoop() {
        gameLoop = new Thread(() -> {
            while (true) {
                for (Bird bird : birds) {
                    bird.update();
                }


                gameScreen.repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return gameLoop;
    }
}
