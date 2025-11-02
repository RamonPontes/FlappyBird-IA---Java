package neuralNetwork;

public class NeuronInput {
    private double value;
    private NeuronWeight weight;

    private final Neuron fromNeuron;
    private final Neuron toNeuron;

    public NeuronInput(Neuron fromNeuron, Neuron toNeuron, double value) {
        this.fromNeuron = fromNeuron;
        this.toNeuron = toNeuron;
        this.value = value;
        this.weight = new NeuronWeight(toNeuron.getInputSize());
    }

    public double getValue() {
        return value;
    }

    public NeuronWeight getWeight() {
        return weight;
    }
}
