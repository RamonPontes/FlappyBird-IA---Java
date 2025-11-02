package game;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {
    private double SCREEN_WIDTH;
    private double SCREEN_HEIGHT;

    public GameScreen(double screenWidth, double screenHeight) {
        this.SCREEN_WIDTH = screenWidth;
        this.SCREEN_HEIGHT = screenHeight;

        setTitle("FlappyBird IA - Java");
        setSize((int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
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
        }
    }
}
