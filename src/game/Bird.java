package game;

import java.awt.*;

public class Bird {
    private double x;
    private double y;
    private double width;
    private double height;
    private double velocity;
    private double gravity;
    private double jumpStrength;

    public Bird(double x, double y, double width, double height, double velocity, double gravity, double jumpStrength) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.gravity = gravity;
        this.jumpStrength = jumpStrength;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    public void update() {
        velocity += gravity;
        y += velocity;
    }

    public void jump() {
        velocity = -jumpStrength * 0.3;
    }
}
