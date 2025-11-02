import java.util.Random;

public class NeuronWeight {
    private static final Random RANDOM = new Random();
    private double weight;
    private final int InputSize;

    public NeuronWeight(int inputSize) {
        this.InputSize = inputSize;

        if (inputSize <= 0) {
            this.weight = RANDOM.nextGaussian() * Math.sqrt(2.0 / InputSize); // He initialization for ReLU activation
        } else {
            this.weight = 0.0; // No inputs, weight is set to 0
        }
    }
}
