package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    //  Screen dimensions
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;

    // Bird configuration
    public static final double BIRD_WIDTH = 34;
    public static final double BIRD_HEIGHT = 24;
    public static final double DEFAULT_VELOCITY = 0;
    public static final double GRAVITY = 0.5;
    public static final double JUMP_STRENGTH = -10;

    public static final int POPULATION_SIZE = 50;

    List<Bird> birds;

    public Game() {
        birds = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            birds.add(new Bird(100, SCREEN_HEIGHT / 2, BIRD_WIDTH, BIRD_HEIGHT, DEFAULT_VELOCITY, GRAVITY, JUMP_STRENGTH));
        }

        GameScreen gameScreen = new GameScreen(SCREEN_WIDTH, SCREEN_HEIGHT, birds);
    }
}
