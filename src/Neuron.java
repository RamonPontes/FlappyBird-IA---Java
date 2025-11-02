import java.util.List;

public class Neuron {
    private List<NeuronInput> inputs;

    private final int InputSize;
    private double bias;

    public Neuron(int InputSize) {
        this.InputSize = InputSize;
        this.bias = Math.random() * 2 - 1; // Initialize bias to a random value between -1 and 1
    }

    public int getInputSize() {
        return InputSize;
    }
}
