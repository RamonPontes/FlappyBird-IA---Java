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

    // Pipe configuration
    private static final double PIPE_WIDTH = 40;
    private static final double PIPE_GAP = 150;
    private static final double PIPE_VELOCITY = 2;
    private static final double PIPE_SPAWN_INTERVAL = 2000;

    // Population size
    private static final int POPULATION_SIZE = 50;

    private List<Bird> birds;
    private List<Pipe> pipes;
    private Thread gameLoop;
    private GameScreen gameScreen;
    private long lastPipeTime = 0;

    public Game() {
        birds = new ArrayList<>();
        pipes = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            birds.add(new Bird(100, SCREEN_HEIGHT / 2, BIRD_WIDTH, BIRD_HEIGHT, DEFAULT_VELOCITY, GRAVITY, JUMP_STRENGTH));
        }

        gameScreen = new GameScreen(SCREEN_WIDTH, SCREEN_HEIGHT, birds, pipes);
        gameLoop().start();
    }

    public void createPipe() {
        pipes.add(new Pipe(
            SCREEN_WIDTH,
            PIPE_WIDTH,
            SCREEN_HEIGHT,
            PIPE_VELOCITY,
            PIPE_GAP,
            Math.random() * (SCREEN_HEIGHT - 200) + 50
        ));
    }

    public Thread gameLoop() {
        gameLoop = new Thread(() -> {
            while (true) {
                createPipesLoop();
                updatePositions();

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

    public void createPipesLoop() {
        long now = System.currentTimeMillis();
        if (now - lastPipeTime > PIPE_SPAWN_INTERVAL) {
            createPipe();
            lastPipeTime =  now;
        }
    }

    public void updatePositions() {
        for (Bird bird : birds) {
            bird.update();
        }

        for (Pipe pipe : pipes) {
            pipe.update();
        }
    }
}
