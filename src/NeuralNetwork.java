import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private static final int INPUTS_SIZE = 3; // Number of input features
    private static final int HIDDEN_LAYERS = 2; // Number of hidden layers
    private static final int NEURONS_PER_HIDDEN_LAYER = 4; // Neurons per hidden layer
    private static final int OUTPUTS_SIZE = 3; // Number of output classes

    private List<Neuron> inputLayerNeurons;
    private List<List<Neuron>> hiddenLayerNeurons;
    private List<Neuron> outputLayerNeurons;

    public NeuralNetwork() {
        inputLayerNeurons = new ArrayList<>();
        hiddenLayerNeurons = new ArrayList<>();
        outputLayerNeurons = new ArrayList<>();

        // Initialize neurons in each layer

        for (int i = 0; i < INPUTS_SIZE; i++) {
            inputLayerNeurons.add(new Neuron(0)); // Input neurons have no inputs
        }

        for (int i = 0; i < HIDDEN_LAYERS; i++) {
            List<Neuron> hiddenLayer = new ArrayList<>();
            int inputSize = (i == 0) ? INPUTS_SIZE : NEURONS_PER_HIDDEN_LAYER; // First hidden layer takes input from input layer
            for (int j = 0; j < NEURONS_PER_HIDDEN_LAYER; j++) {
                hiddenLayer.add(new Neuron(inputSize));
            }
            hiddenLayerNeurons.add(hiddenLayer);
        }

        for (int i = 0; i < OUTPUTS_SIZE; i++) {
            outputLayerNeurons.add(new Neuron(NEURONS_PER_HIDDEN_LAYER));
        }
    }
}
