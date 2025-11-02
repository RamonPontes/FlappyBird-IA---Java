package neuralNetwork;

public class NeuronInput {
    private double value;
    private NeuronWeight weight;

    private final Neuron fromNeuron;
    private final Neuron toNeuron;

    public NeuronInput(Neuron fromNeuron, Neuron toNeuron) {
        this.value = 0.0;

        this.fromNeuron = fromNeuron;
        this.toNeuron = toNeuron;
        this.weight = new NeuronWeight(toNeuron.getInputSize());
    }

    public double getValue() {
        return value;
    }

    public NeuronWeight getWeight() {
        return weight;
    }

    public Neuron getToNeuron() {
        return toNeuron;
    }

    public Neuron getFromNeuron() {
        return fromNeuron;
    }
}
