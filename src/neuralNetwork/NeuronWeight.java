package neuralNetwork;

import java.util.Random;

public class NeuronWeight {
    private static final Random RANDOM = new Random();
    private double value;
    private final int InputSize;

    public NeuronWeight(int InputSize) {
        this.InputSize = InputSize;

        if (InputSize > 0) {
            this.value = RANDOM.nextGaussian() * Math.sqrt(2.0 / InputSize); // He initialization for ReLU activation
        } else {
            this.value = 0.0; // No inputs, weight is set to 0
        }
    }

    public double getValue() {
        return value;
    }
}
