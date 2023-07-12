package src.transport;


import java.util.List;

public class Transport {
    private final Graph graph;
    private final ShortestPath shortestPath;
    private final String[] locationNames;

    public Transport(Graph graph, String[] locationNames) {
        this.graph = graph;
        this.shortestPath = new ShortestPath(graph);
        this.locationNames = locationNames;
    }

    public void addEdge(int source, int destination, double weight) {
        graph.addEdge(source, destination, weight);
    }

    public List<Integer> findShortestPath(int source, int destination) {
        shortestPath.findShortestPath(source);
        return shortestPath.getShortestPath(destination);
    }

    public double getShortestDistance(int source, int destination) {
        shortestPath.findShortestPath(source);
        return shortestPath.getShortestDistance(destination);
    }

    public List<List<Integer>> getBestThreeShortestPaths(int source, int destination) {
        shortestPath.findShortestPath(source);
        return shortestPath.getBestThreeShortestPaths(destination);
    }

    public static void main(String[] args) {
        // Create a graph and add edges
        Graph graph = new Graph(20);
        Transport solver = new Transport(graph,
                new String[]{
                        "Main Gate Entrance",  //0
                        "JQB",                 // 1
                        "Bank",//2
                        "Akuafo Roundabout",//3
                        "Bush Canteen", //4
                        "Night Market", //5
                        "Balm Library", //6
                        "Central Cafeteria",    //7
                        "Commonwealth Entrance",//8
                        "Physics Department",   //9
                        "CS Department",        //10
                        "NNB",                  //11
                        "Law School",           //12
                        "KAB",                  //13
                        "LOT",                  //14
                        "Legon Hall - Main",    //15
                        "Cedi Conference Center",//16
                        "GCB Hall",             //17
                        "Legon Hall Anex B",    //18
                        "UGCS"});               //19

        solver.addEdge(0, 1, 5.6);
        solver.addEdge(0, 2, 3.3);
        solver.addEdge(1, 3, 4.1);
        solver.addEdge(1, 4, 2);
        solver.addEdge(2, 3, 1);
        solver.addEdge(2, 4, 6);
        solver.addEdge(3, 5, 5);
        solver.addEdge(4, 5, 7);
        solver.addEdge(4, 6, 3);
        solver.addEdge(5, 6, 2);

        // Find the shortest path from source to destination
        int source = 4;
        int destination = 8;
        List<Integer> shortestPath = solver.findShortestPath(source, destination);
        double shortestDistance = solver.getShortestDistance(source, destination);
        List<List<Integer>> bestPaths = solver.getBestThreeShortestPaths(source, destination);

        // Print the results
        System.out.print("Shortest Path from " + source + " to " + destination + ": ");
        for (int vertex : shortestPath) {
            System.out.print(solver.locationNames[vertex] + " -> ");
        }
        System.out.println();

//        System.out.print("Shortest Path from " + solver.locationNames[source] + " to " +
//                solver.locationNames[destination] + ": ");
//        for (int vertex : shortestPath) {
//            System.out.print(solver.locationNames[vertex] + " -> ");
//        }
//        System.out.println();


        System.out.println("Shortest Distance from " + solver.locationNames[source] + " to " +
                solver.locationNames[destination] + ": " + shortestDistance);
        System.out.println("Best Three Shortest Paths to " + solver.locationNames[destination] + ": ");


        if (bestPaths.isEmpty()) {
            System.out.println("No paths found.");
        } else {
            for (List<Integer> path : bestPaths) {
                System.out.print("Path: ");
                StringBuilder pathBuilder = new StringBuilder();

                for (int vertex : path) {
                    if (vertex >= 0 && vertex < solver.locationNames.length) {
                        pathBuilder.append(solver.locationNames[vertex]).append(" -> ");
                    }
                }

                if (pathBuilder.length() > 0) {
                    pathBuilder.setLength(pathBuilder.length() - 4); // Remove the last " -> "
                    System.out.println(pathBuilder);
                }
            }
        }
    }

}
