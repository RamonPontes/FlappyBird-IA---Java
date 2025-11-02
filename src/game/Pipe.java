package game;

import java.awt.*;
import java.util.List;

public class Pipe {
    private double x;
    private double width;
    private double height;
    private double velocity;
    private double gap;
    private double gapY;

    public Pipe(double x, double width, double height, double velocity, double gap, double gapY) {
        this.x = x;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.gap = gap;
        this.gapY = gapY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        // Draw top pipe
        g.fillRect((int) x, 0, (int) width, (int) (height - gapY));

        // Draw bottom pipe
        g.fillRect((int) x, (int) (height - gapY + gap), (int) width, (int) (gapY - gap));
    }

    public void update() {
        x -= velocity;
    }

    public List<Rectangle> getRectangles() {
        Rectangle topPipe = new Rectangle((int) x, 0, (int) width, (int) (height - gapY));
        Rectangle bottomPipe = new Rectangle((int) x, (int) (height - gapY + gap), (int) width, (int) (gapY - gap));
        return List.of(topPipe, bottomPipe);
    }

    public double getX() {
        return x;
    }
}
