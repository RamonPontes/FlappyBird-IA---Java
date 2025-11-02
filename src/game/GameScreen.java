package game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameScreen extends JFrame {
    private double SCREEN_WIDTH;
    private double SCREEN_HEIGHT;

    private List<Bird> birds;
    private List<Pipe> pipes;

    public GameScreen(double screenWidth, double screenHeight, List<Bird> birds, List<Pipe> pipes) {
        this.SCREEN_WIDTH = screenWidth;
        this.SCREEN_HEIGHT = screenHeight;
        this.birds = birds;
        this.pipes = pipes;

        setTitle("FlappyBird IA - Java");
        setSize((int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        add(new GamePanel());
    }

    public class GamePanel extends JPanel {
        public GamePanel() {
            setFocusable(true);
            requestFocusInWindow();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.BLACK);

            for (Bird bird : birds) {
                bird.draw(g);
            }

            for (Pipe pipe : pipes) {
                pipe.draw(g);
            }
        }
    }
}
