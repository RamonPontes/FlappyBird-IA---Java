package neuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NeuralNetwork {
    private static final int INPUTS_SIZE = 5; // Number of input features
    private static final int HIDDEN_LAYERS = 1; // Number of hidden layers
    private static final int NEURONS_PER_HIDDEN_LAYER = 5; // Neurons per hidden layer
    private static final int OUTPUTS_SIZE = 1; // Number of output classes

    private List<Neuron> inputLayerNeurons;
    private List<List<Neuron>> hiddenLayerNeurons;
    private List<Neuron> outputLayerNeurons;


    // Constructor to initialize the neural network structure
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

    // Feedforward method to propagate inputs through the network
    public void feedForward(List<NeuronInput> inputs) {
        for (Neuron neuron : inputLayerNeurons) {
            neuron.setInputs(inputs.stream()
                    .filter(
                            e -> e.getFromNeuron() == neuron)
                    .collect(Collectors.toList()));
        }

        for (int i = 0; i < hiddenLayerNeurons.size(); i++) {
            List<Neuron> currentLayer = hiddenLayerNeurons.get(i);
            List<Neuron> previousLayer = (i == 0) ? inputLayerNeurons : hiddenLayerNeurons.get(i - 1);

            for (Neuron neuron : currentLayer) {
                List<NeuronInput> neuronInputs = previousLayer.stream()
                        .map(prevNeuron -> new NeuronInput(prevNeuron, neuron))
                        .collect(Collectors.toList());
                neuron.setInputs(neuronInputs);
            }
        }

        for (Neuron neuron : outputLayerNeurons) {
            List<NeuronInput> neuronInputs = hiddenLayerNeurons.get(hiddenLayerNeurons.size() - 1).stream()
                    .map(prevNeuron -> new NeuronInput(prevNeuron, neuron))
                    .collect(Collectors.toList());
            neuron.setInputs(neuronInputs);
        }
    }

    // Get the outputs of the network as a map of output index to output value
    public Map<Integer, Double> getOutputs() {
        return outputLayerNeurons.stream()
                .collect(Collectors.toMap(
                        outputLayerNeurons::indexOf,
                        neuron -> neuron.getOutput(true)
                ));
    }
}
