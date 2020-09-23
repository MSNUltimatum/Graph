import abstractClasses.Graph;
import abstractClasses.OrientedAbstractClass;
import graphClasses.NotOrientedGraph;
import graphClasses.OrientedGraph;
import graphClasses.OrientedWeightedGraph;

import java.util.Scanner;

public class Main {
    private static Graph graph;
    private static final Scanner sc = new Scanner(System.in);
    private static final GraphFactory<Integer> graphFactory = new GraphFactory<>();
    
    public static void main(String[] args) {
        GraphType graphChoise = getGraphChoise();;
        graph = graphFactory.makeGraph(graphChoise);
        actionCycle();
    }

    private static GraphType getGraphChoise() {
        System.out.println("Hello, chose the graph type:" +
                "\n1 - Not Oriented not weighted graph" +
                "\n2 - Not Oriented weighted graph" +
                "\n3 - Oriented not weighted graph" +
                "\n4 - Oriented weighted graph");
        String s = sc.nextLine();
        return switch (s){
            case "1" -> GraphType.NOTORIENTED;
            case "2" -> GraphType.NOTORIENTEDWEIGHTED;
            case "3" -> GraphType.ORIENTED;
            case "4" -> GraphType.ORIENTEDWEIGHTED;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }

    private static void actionCycle() {
        while (true){
            printMenu();
            String s1 = sc.nextLine();
            switch (s1){
                case "1":
                    insertVertex();
                    break;
                case "2":
                    try {
                        inserEdge();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    deleteVertex();
                    break;
                case "4":
                    deleteEdge();
                    break;
                case "5":
                    graph.printGraphToConsole();
                    break;
                case "6":
                    try {
                        printAllComonIncomingVertexes();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "7":
                    printAllIncomingAndOutgoingVetexes();
                    break;
                case "8":
                    printGraphWithoutEdgesBetweenSamePowerVertexes();
                    break;
                case "0":
                    return;
            }
        }
    }

    private static void printGraphWithoutEdgesBetweenSamePowerVertexes() {
        Graph graphWithoutEdges = graphFactory.makeGraphWithoutEdgesBetweenSamePowerVertexes(Main.graph);
        graphWithoutEdges.printGraphToConsole();
    }

    private static void printAllIncomingAndOutgoingVetexes() {
        if(graph instanceof OrientedGraph || graph instanceof OrientedWeightedGraph){
            int v1F = sc.nextInt();
            ((OrientedAbstractClass<Integer>) graph).findAllIncomingAndOutgoingVertex(v1F);
        }
    }

    private static void printAllComonIncomingVertexes() {
        if (graph instanceof OrientedGraph || graph instanceof OrientedWeightedGraph) {
            int v1F = sc.nextInt();
            int v2F = sc.nextInt();
            ((OrientedAbstractClass<Integer>) graph).findCommonIncomingVertex(v1F, v2F);
        }
    }

    private static void deleteEdge() {
        int v1d = sc.nextInt();
        int v2d = sc.nextInt();
        graph.deleteEdge(v1d, v2d);
    }

    private static void deleteVertex() {
        int v = sc.nextInt();
        graph.deleteVertex(v);
    }

    private static void insertVertex() {
        int vert = sc.nextInt();
        graph.addVertex(vert);
    }

    private static void inserEdge() {
        int v1 = sc.nextInt(), v2 = sc.nextInt();
        if (graph instanceof OrientedGraph || graph instanceof NotOrientedGraph) {
            graph.addEdge(v1, v2);
        } else {
            int weight = sc.nextInt();
            graph.addEdge(v1, v2, weight);
        }
    }

    private static void printMenu() {
        System.out.println("Choose the operation:" +
                "\n1 - add vertex" +
                "\n2 - add edge" +
                "\n3 - delete vertex" +
                "\n4 - delete edge" +
                "\n5 - print graph" +
                "\n6 - find common incoming vertex(19 task)" +
                "\n7 - find incoming and outgoing vertexes(13 task)" +
                "\n8 - print graph without edges between same power vertexes" +
                "\n0 - exit");
    }
}
