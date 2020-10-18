import graphClasses.FordBellmanAlgorithm;
import graphClasses.GraphFactory;
import graphClasses.GraphType;
import graphClasses.OrientedWeightedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FordBellmonTest {
    OrientedWeightedGraph<Integer> graph;
    GraphFactory<Integer> gf;
    FordBellmanAlgorithm fb;
    @BeforeEach
    public void init(){
        gf = new GraphFactory<>();
        graph = new OrientedWeightedGraph<>();
    }

    @Test
    public void fordBellmanTest(){
        for (int i = 0; i < 7; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 0, 2);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, -100);
        graph.addEdge(0, 3, 5);
        graph.addEdge(0, 4, 4);
        graph.addEdge(1, 6, 3);
        graph.addEdge(2, 5, 7);
        fb = new FordBellmanAlgorithm(graph);

        List<Integer> negateCycle = fb.getNegateCycle();
        List<Integer> result = new ArrayList<>();
        result.add(2);
        result.add(1);
        result.add(0);
        result.add(2);
        assertEquals(result, negateCycle);
    }

    @Test
    public void fordBellmanSecondTest(){
        for (int i = 1; i <= 4; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 2, -2);
        graph.addEdge(2, 3, 0);
        graph.addEdge(3, 1, 1);
        graph.addEdge(3, 4, 0);
        fb = new FordBellmanAlgorithm(graph);

        List<Integer> negateCycle = fb.getNegateCycle();
        List<Integer> result = new ArrayList<>();
        result.add(3);
        result.add(1);
        result.add(2);
        result.add(3);

        assertEquals(result, negateCycle);
    }
}
