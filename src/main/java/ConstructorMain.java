import abstractClasses.Graph;
import graphClasses.NotOrientedGraph;

import java.io.FileNotFoundException;

public class ConstructorMain {
    public static void main(String[] args) throws FileNotFoundException {
        Graph<Integer> graph = new NotOrientedGraph<>("graphinput.txt", Integer.class);
        graph.printGraphToConsole();
    }
}
