import graphClasses.FloydWarshallAlgorithm;
import graphClasses.GraphFactory;
import graphClasses.GraphType;
import graphClasses.NoOrientedWeightedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloydAlgorithmTest {
    NoOrientedWeightedGraph<Integer> graph;
    GraphFactory<Integer> gf;
    FloydWarshallAlgorithm fut;

    @BeforeEach
    public void init(){
        gf = new GraphFactory<>();
        graph = new NoOrientedWeightedGraph<>();
    }

    @Test
    public void floydTest(){
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(0, 1, 0.5);
        graph.addEdge(2, 1, 0.5);
        graph.addEdge(0, 2, 0.5);

        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 4, 1);
        graph.addEdge(0, 5, 1);

        graph.addEdge(5, 3, 3);
        graph.addEdge(4, 3, 3);
        graph.addEdge(4, 5, 3);
        fut = new FloydWarshallAlgorithm(graph);
        List<Integer> centerOfGraph = fut.findCenterOfGraph();
        List<Integer> reult = new ArrayList<>();
        reult.add(0);
        reult.add(1);
        reult.add(2);
        assertEquals(reult, centerOfGraph);
    }
}
