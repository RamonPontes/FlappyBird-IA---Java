import java.util.Random;

public class NeuronWeight {
    private static final Random RANDOM = new Random();
    private double weight;
    private final int InputSize;

    public NeuronWeight(int inputSize) {
        this.InputSize = inputSize;
        this.weight = RANDOM.nextGaussian() * Math.sqrt(2.0 / InputSize);
    }
}
