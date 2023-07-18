package src.sem_project;

import java.util.List;

public class ArrivalTimeCalculator {
    private final List<List<Edge>> adjacencyList;
    Main.Solver solver;

    public ArrivalTimeCalculator(Main.Solver solver) {
        this.solver = solver.getSolver();
        adjacencyList = solver.graph.getAdjacencyList();
    }

    public double calculateArrivalTime(int source, int destination) {
        List<Edge> connections = adjacencyList.get(source);
        double distance = 0.0;
        double speed = 0.0;
        for (Edge connection : connections) {
            int connDestination = connection.getDestination();
            System.out.println("Dest: " + connDestination);
            if(connDestination == destination) {
                distance = connection.getWeight();
                speed = connection.getSpeed();
                System.out.println("Distance: " + distance);
            }
        }

        return distance / speed;
    }


}
