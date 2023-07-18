package src.sem_project;

public class Edge {
    private final int destination;
    private final double weight;
    private final double speed;

    public Edge(int destination, double weight, double speed) {
        this.destination = destination;
        this.weight = weight;
        this.speed = speed;
    }

    public int getDestination() {
        return destination;
    }

    public double getSpeed() {
        return speed;
    }

    public double getWeight() {
        return weight;
    }
}

