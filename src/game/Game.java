package game;

public class Game {
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;

    public Game() {
        GameScreen gameScreen = new GameScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
