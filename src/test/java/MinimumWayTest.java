import abstractClasses.Graph;
import abstractClasses.NonOrientedAbstractGraph;
import graphClasses.GraphFactory;
import graphClasses.GraphType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
public class MinimumWayTest {
    GraphFactory<Integer> gf = new GraphFactory<>();
    @Test
    public void minWay(){
        Graph<Integer> graph = gf.makeGraph(GraphType.NOTORIENTEDWEIGHTED);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2, 0.5);
        graph.addEdge(2, 3, 0.5);
        graph.addEdge(3, 4, 0.5);
        graph.addEdge(4, 5, 0.5);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 5, 0.5);
        graph.addEdge(1, 4, 1);
        Map<Integer, Double> result = new HashMap<>();
        result.put(1, 0d);
        result.put(2, 0.5);
        result.put(3, 1d);
        result.put(4, 1d);
        result.put(5, 0.5d);
        Map<? extends Object, ? extends Double> distances = ((NonOrientedAbstractGraph) graph).findDistances(1);
        assertEquals(result, distances);
    }

    @Test
    public void countOfMinWays(){
        Graph<Integer> graph = gf.makeGraph(GraphType.NOTORIENTEDWEIGHTED);
        for (int i = 0; i < 8; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(0, 1, 0.5);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 0.5);

        graph.addEdge(3, 4, 0.5);
        graph.addEdge(4, 6, 0.5);
        graph.addEdge(3, 5, 2);
        graph.addEdge(5, 6, 1);
        graph.addEdge(3, 7, 3);
        Map<Integer, Integer> result = new HashMap<>();
        result.put(0, 1);
        result.put(1, 1);
        result.put(2, 1);
        result.put(3, 2);
        result.put(4, 2);
        result.put(5, 4);
        result.put(6, 2);
        result.put(7, 2);

        Map<Integer, Integer> minWays = ((NonOrientedAbstractGraph) graph).countOfMinimumWays(0);
        assertEquals(result, minWays);
    }
}
