import abstractClasses.Graph;
import graphClasses.GraphFactory;
import graphClasses.GraphType;

import java.io.FileNotFoundException;

public class ConstructorMain {
    public static void main(String[] args) throws FileNotFoundException {
        GraphFactory<Integer> gf = new GraphFactory<>();
        Graph<Integer> graph = gf.makeGraph(GraphType.NOTORIENTED, "inputgraph.txt", Integer.class);
        graph.printGraphToConsole();
    }
}
