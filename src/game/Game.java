package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    //  Screen dimensions
    public static final double SCREEN_WIDTH = 800;
    public static final double SCREEN_HEIGHT = 1000;

    // Bird configuration
    private static final double BIRD_WIDTH = 30;
    private static final double BIRD_HEIGHT = 30;
    private static final double DEFAULT_VELOCITY = 0;
    private static final double GRAVITY = 0.5;
    private static final double JUMP_STRENGTH = 30;

    // Pipe configuration
    private static final double PIPE_WIDTH = 40;
    private static final double PIPE_GAP = 150;
    private static final double PIPE_VELOCITY = 2;
    private static final double PIPE_SPAWN_INTERVAL = 3000;

    // Population size
    private static final int POPULATION_SIZE = 1;

    private static List<Bird> birds;
    private static List<Pipe> pipes;
    private static Thread gameLoop;
    private static GameScreen gameScreen;
    private static long lastPipeTime = 0;
    private static boolean running;

    public Game() {
        birds = new ArrayList<>();
        pipes = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            birds.add(new Bird(100, SCREEN_HEIGHT / 2, BIRD_WIDTH, BIRD_HEIGHT, DEFAULT_VELOCITY, GRAVITY, JUMP_STRENGTH));
        }

        gameScreen = new GameScreen(SCREEN_WIDTH, SCREEN_HEIGHT, birds, pipes, this);
        gameLoop = gameLoop();
        gameLoop.start();

        running = true;
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
            while (running) {
                createPipesLoop();
                updatePositions();
                verifyCollisions();
                getNextAction();

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

    public void getNextAction() {
        for (Bird bird : birds) {
            bird.getNextAction();
        }
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

    public void verifyCollisions() {
        for (Bird bird : birds) {
            Pipe nextPipe = getNextPipe(bird);

            if (nextPipe != null) {
                for (Rectangle rect : nextPipe.getRectangles()) {
                    if (bird.getRectangle().intersects(rect)) {
                        running = false;
                    }
                }
            }

            if (bird.getY() <= 0) {
                running = false;
            }
            if (bird.getY() + BIRD_HEIGHT >= SCREEN_HEIGHT) {
                running = false;
            }

            if (!running) {
                restart();
            }
        }
    }

    public void restart() {
        birds.clear();
        pipes.clear();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            birds.add(new Bird(100, SCREEN_HEIGHT / 2, BIRD_WIDTH, BIRD_HEIGHT, DEFAULT_VELOCITY, GRAVITY, JUMP_STRENGTH));
        }

        lastPipeTime = 0;
        running = true;
    }

    public static Pipe getNextPipe(Bird bird) {
        for (Pipe pipe : pipes) {
            if (bird.getX() < pipe.getX()) {
                return pipe;
            }
        }

        return null;
    }

    public void setPaused() {
        running = !running;
    }
}
