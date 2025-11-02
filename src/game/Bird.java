package game;

import neuralNetwork.NeuralNetwork;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bird {
    private double x;
    private double y;
    private double width;
    private double height;
    private double velocity;
    private double gravity;
    private double jumpStrength;
    private NeuralNetwork neuralNetwork;

    public Bird(double x, double y, double width, double height, double velocity, double gravity, double jumpStrength) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.gravity = gravity;
        this.jumpStrength = jumpStrength;
        this.neuralNetwork = new NeuralNetwork();
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

    public void getNextAction() {
        neuralNetwork.feedForward(getInputs());
        List<Double> outputs = neuralNetwork.getOutputs();

        if (outputs.get(0) != 0) {
            jump();
        }
    }

    // birdY 0
    // birdVelocityY 1
    // nextPipeX 2
    // nextPipeTopY 3
    // nextPipeBottomY 4
    public List<Double> getInputs() {
        List<Double> results = new ArrayList<>();

        results.add(0, y);
        results.add(1, velocity);

        Pipe nextPipe = Game.getNextPipe(this);

        if (nextPipe != null) {
            results.add(2, nextPipe.getX());
            results.add(3, nextPipe.getGapY());
            results.add(4, nextPipe.getGapY() + nextPipe.getGap());
        } else {
            results.add(2, 0.0);
            results.add(3, 0.0);
            results.add(4, 0.0);

        }

        return results;
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
