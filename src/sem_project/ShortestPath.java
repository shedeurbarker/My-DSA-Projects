package src.sem_project;

import java.util.*;

public class ShortestPath {
    private final Graph graph;
    private final double[] distances;
    private final int[] previous;

    public ShortestPath(Graph graph) {
        this.graph = graph;
        distances = new double[graph.getVertices()];
        previous = new int[graph.getVertices()];
    }

    public void findShortestPath(int source) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Node::getDistance));
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);

        distances[source] = 0;
        priorityQueue.offer(new Node(source, 0));

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();

            if (current.getDistance() > distances[current.getVertex()]) {
                continue;
            }

            List<Edge> neighbors = graph.getAdjacencyList().get(current.getVertex());
            for (Edge edge : neighbors) {
                double newDistance = distances[current.getVertex()] + edge.getWeight();
                if (newDistance < distances[edge.getDestination()]) {
                    distances[edge.getDestination()] = newDistance;
                    previous[edge.getDestination()] = current.getVertex();
                    priorityQueue.offer(new Node(edge.getDestination(), newDistance));
                }
            }
        }
    }

    public double getShortestDistance(int destination) {
        return distances[destination];
    }

    public List<Integer> getShortestPath(int destination) {
        List<Integer> path = new ArrayList<>();
        int current = destination;
        while (current != -1) {
            path.add(0, current);
            current = previous[current];
        }
        return path;
    }
    static class Node {
        private final int vertex;
        private final double distance;

        public Node(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }
    }
}



