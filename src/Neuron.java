import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Neuron {
    // Set activation function
    Function<Double, Double> ACTIVATE_FUNCTION = x -> Math.max(0, x);

    private List<NeuronInput> inputs;

    private final int InputSize;

    private double bias;
    private double output;

    public Neuron(int InputSize) {
        this.inputs = new ArrayList<>();
        this.InputSize = InputSize;
        this.bias = Math.random() * 2 - 1; // Initialize bias to a random value between -1 and 1
        this.output = 0.0;
    }


    // Calculate the output of the neuron
    // Apply activation function if specified
    public double getOutput(boolean applyActivationFunction) {
        if (applyActivationFunction) {
            double sum = bias;
            for (NeuronInput input : inputs) {
                sum += input.getValue() * input.getWeight().getValue();
            }
            sum = ACTIVATE_FUNCTION.apply(sum);

            return sum;
        } else {
            return output;
        }
    }

    public List<NeuronInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<NeuronInput> inputs) {
        this.inputs = inputs;
    }

    public int getInputSize() {
        return InputSize;
    }
}
