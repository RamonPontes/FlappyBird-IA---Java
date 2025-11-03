package neuralNetwork;

import java.util.Random;

public class NeuronWeight {
    private static final double MUTATION_STRENGTH = 0.1;
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

    public void mutateWeight() {
        this.value += Math.random() * MUTATION_STRENGTH * 2 - MUTATION_STRENGTH;
    }

}
