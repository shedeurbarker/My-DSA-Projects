package src.sem_project;


import java.text.DecimalFormat;
import java.util.*;

public class Main {
    private static ShortestPath shortestPath;
    static int source = 0;
    static int vertices = 25;
    static int destination = 0;
    static Graph graph;

//    public void addEdge(int source, int destination, double weight, double speed) {
//        graph.addEdge(source, destination, weight, speed);
//    }

    public static List<Integer> findShortestPath(int source, int destination) {
        shortestPath.findShortestPath(source);
        return shortestPath.getShortestPath(destination);
    }

    public static double getShortestDistance(int source, int destination) {
        shortestPath.findShortestPath(source);
        return shortestPath.getShortestDistance(destination);
    }

//    public List<List<Integer>> getBestThreeShortestPaths(int source, int destination) {
//        shortestPath.findShortestPath(source);
//        return shortestPath.getBestThreeShortestPaths(destination);
//    }

    public static void main(String[] args) {

        graph = new Graph(vertices);
        addEdges();
        Solver mySolverPackaged = new Solver(graph, locations());

        shortestPath = new ShortestPath(graph);

        ArrivalTimeCalculator calculator = new ArrivalTimeCalculator(mySolverPackaged);

        // Initialize the adjacency list
//        adjacencyList = new ArrayList<>(vertices);
//        for (int i = 0; i < vertices; i++) {
//            adjacencyList.add(new ArrayList<>());
//        }

        // Find the shortest path from source to destination
        // User Interactions
        System.out.println("##########################################");
        System.out.println("#       Hello there and Welcome!         #");
        System.out.println("#               GROUP 47                 #");
        System.out.println("#    THE LEGON CAMPUS ROUTE/PATH FINDER  #");
        System.out.println("##########################################");
        System.out.println();
        for (int i= 1; i < locations().length; i++) {
            System.out.println((i) + ". " + locations()[i-1]);
        }
        System.out.println("0. Quiz");
        while (true) {
            System.out.println();
            System.out.println("What Is Your Current Location. Enter Number");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choice: ");
            source = scanner.nextInt() - 1;

            if(source == -1)
                break;

            if(source > -1 && source < locations().length) {
                System.out.println("Where would you like to go. Pick a number");
                System.out.print("Choice: ");
                destination = scanner.nextInt() - 1;
                if(destination == -1)
                    break;
                if(destination > -1 && destination < locations().length) {
                    System.out.println();
                    System.out.println("Shortest Route Would Be:");
                    double arrivalTime = 0;
                    List<Integer> shortestPath = findShortestPath(source, destination);
                    double shortestDistance = getShortestDistance(source, destination);
                    StringBuilder path = new StringBuilder();
                    for (int i = 0; i < shortestPath.size(); i++) {
                        System.out.println("Distance: " + shortestPath.get(i));
                        path.append(locations()[shortestPath.get(i)]);
                        if(i < (shortestPath.size() -1))
                            path.append(" -> ");

                        List<Edge> connections = graph.getAdjacencyList().get(source);
//                        System.out.println("Dest: " + connections.get(0).getSpeed());
//                        double distance;
//                        double speed;
//                        for (Edge connection : connections) {
//                            int connDestination = connection.getDestination();
//
//                            if(connDestination == destination) {
//                                distance = connection.getWeight();
//                                speed = connection.getSpeed();
//                                System.out.println("Distance: " + distance);
//                                arrivalTime += distance / speed;
//                                break;
//                            }
//                        }
                    }
                    System.out.println(path);
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String shortenedValue = decimalFormat.format(shortestDistance);
                    System.out.println("Distance is Apprx. " + shortenedValue + "km");

//                    double arrivalTime = calculator.calculateArrivalTime(source, destination);
                    List<Edge> connections = graph.getAdjacencyList().get(source);
                    double distance = 0.0;
                    double speed = 0.0;

//                    for (Edge connection : connections) {
//                        int connDestination = connection.getDestination();
//                        System.out.println("Dest: " + connDestination);
//                        if(connDestination == destination) {
//                            distance = connection.getWeight();
//                            speed = connection.getSpeed();
//                            System.out.println("Distance: " + distance);
//                            arrivalTime = distance / speed;
//                            break;
//                        }
//                    }
//                    System.out.println("Arrival time to destination: " + arrivalTime + " hours");

//                    List<List<Integer>> bestPaths = solver.getBestThreeShortestPaths(source, destination);
////                    System.out.println(bestPaths.toString());
//                    for (List<Integer> vertex : bestPaths) {
//                        //for(int v : vertex)
//                        System.out.println(vertex + " -> ");
//                    }
                }
                else {
                    System.out.println("Choose the right starting location");
                }
            }
            else {
                System.out.println("Choose the right destinations");
            }
        }

//        List<List<Integer>> bestPaths = solver.getBestThreeShortestPaths(source, destination);
//        System.out.print("Shortest Path from " + solver.locationNames[source] + " to " +
//                solver.locationNames[destination] + ": ");
//        for (int vertex : shortestPath) {
//            System.out.print(solver.locationNames[vertex] + " -> ");
//        }
//        System.out.println();


//        System.out.println("Best Three Shortest Paths to " + solver.locationNames[destination] + ": ");

/*
        if (bestPaths.isEmpty()) {
            System.out.println("No paths found.");
        }
        else {
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
        */
    }

    public static class Solver {
        Graph graph;
        String[] locationNames;

        public Solver(Graph graph, String[] locationNames) {
            this.graph = graph;
            this.locationNames = locationNames;
        }

        public Solver getSolver() {
            return new Solver(this.graph, this.locationNames);
        }
    }

    private static String[] locations() {
        return new String[]{
                "Main Gate Entrance",           //0
                "JQB",                          //1
                "Bush Canteen",                 //2
                "Night Market",                 //3
                "UG JHS",                       //4
                "School of Performing Arts",    //5
                "KAB",                          //6
                "CS Department",                //7
                "NNB",                          //8
                "Chemistry Dept.",              //9
                "UGCS",                         //10
                "Akuafo Roundabout",            //11
                "Balm Library",                 //12
                "Legon Hall - Main",            //13
                "Legon Hall - Annex A",         //14
                "Legon Hall - Annex B",         //15
                "Central Cafeteria",            //16
                "Akuafo Hall - Annex C",        //17
                "Cal Bank",                     //18
                "Volta Hall",                   //19
                "Commonwealth Gate",            //20
                "Nursing School",               //21
                "GCB Lecture Building",         //22
                "NNB",                          //23
                "NB",                           //24
        };
    }

    private static void addEdges() {
        addConnection(0, 1, 0.250, 20);
        addConnection(0, 2, 0.500, 40);
        addConnection(0, 5, 0.240, 40);
        addConnection(0, 11, 0.500, 45);
        addConnection(1, 6, 0.500, 20);
        addConnection(2, 5, 0.260, 40);
        addConnection(3, 5, 0.950, 40);
        addConnection(3, 4, 0.450, 20);
        addConnection(6, 7, 0.350, 40);
        addConnection(6, 9, 0.400, 40);
        addConnection(7, 9, 0.400, 45);
        addConnection(7, 23, 0.600, 30);
        addConnection(9, 11, 0.300, 40);
        addConnection(9, 10, 0.240, 40);
        addConnection(9, 12, 0.260, 40);
        addConnection(11, 12, 0.350, 45);
        addConnection(11, 13, 0.550, 45);
        addConnection(12, 13, 0.450, 20);
        addConnection(12, 18, 0.300, 45);
        addConnection(13, 14, 0.550, 45);
        addConnection(14, 15, 0.200, 40);
        addConnection(15, 4, 0.550, 40);
        addConnection(15, 16, 0.160, 40);
        addConnection(16, 17, 0.280, 40);
        addConnection(2, 17, 0.900, 40);
        addConnection(11, 17, 0.350, 40);
        addConnection(18, 10, 0.400, 40);
        addConnection(18, 19, 0.280, 40);
        addConnection(18, 20, 0.170, 40);
        addConnection(19, 10, 0.400, 40);
        addConnection(10, 21, 400, 40);
        addConnection(10, 9, 0.350, 40);
        addConnection(21, 22, 0.400, 40);
        addConnection(22, 9, 0.750, 40);
        addConnection(22, 24, 0.400, 40);
        addConnection(24, 23, 0.500, 40);
        addConnection(24, 7, 0.500, 40);
        addConnection(20, 14, 0.500, 40);
        addConnection(20, 21, 0.400, 40);
        addConnection(22, 23, 0.090, 40);
    }

    private static void addConnection(int source, int destination, double weight, double speed) {
        graph.addEdge(source, destination, weight, speed);
        graph.addEdge(destination, source, weight, speed);
    }

}