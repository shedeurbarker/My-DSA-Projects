package src.sem_project;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int vertices;
    private final List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, double weight, double speed) {
        Edge edge = new Edge(destination, weight, speed);
        adjacencyList.get(source).add(edge);
    }

    public int getVertices() {
        return vertices;
    }

    public List<List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
}