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
        Map distances = ((NonOrientedAbstractGraph) graph).findDistances(1);
        assertEquals(result, distances);

    }
}
