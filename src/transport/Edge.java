package src.transport;

public class Edge {
    private final int destination;
    private final double weight;

    public Edge(int destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public int getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}

