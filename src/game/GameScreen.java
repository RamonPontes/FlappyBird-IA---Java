package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class GameScreen extends JFrame {
    private double SCREEN_WIDTH;
    private double SCREEN_HEIGHT;

    private List<Bird> birds;
    private List<Pipe> pipes;

    private Game game;

    public GameScreen(double screenWidth, double screenHeight, List<Bird> birds, List<Pipe> pipes, Game game) {
        this.SCREEN_WIDTH = screenWidth;
        this.SCREEN_HEIGHT = screenHeight;
        this.birds = birds;
        this.pipes = pipes;
        this.game = game;

        setTitle("FlappyBird IA - Java");
        setSize((int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                verifyKeyPress(e);
            }
        });
        add(new GamePanel());
    }

    private class GamePanel extends JPanel {
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

    private void verifyKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_P:
                game.setPaused();
                break;
            case KeyEvent.VK_SPACE:
                for (Bird b : birds) {
                    b.jump();
                }
                break;
            default:
                break;
        }
    }
}
