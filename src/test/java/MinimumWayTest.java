import abstractClasses.Graph;
import abstractClasses.NoOrientedAbstractGraph;
import graphClasses.GraphFactory;
import graphClasses.GraphType;
import graphClasses.MinimumWaysWithBFS;
import graphClasses.NoOrientedWeightedGraph;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
public class MinimumWayTest {
    GraphFactory<Integer> gf = new GraphFactory<>();
    @Test
    public void minWay(){
        NoOrientedWeightedGraph<Integer> graph = new NoOrientedWeightedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 0.5);
        graph.addEdge(3, 5, 0.5);
        graph.addEdge(2, 4, 1);
        graph.addEdge(5, 4, 0.5);
        Map<Integer, Double> result = new HashMap<>();
        result.put(1, 0d);
        result.put(2, 0.5);
        result.put(3, 1d);
        result.put(4, 1d);
        result.put(5, 0.5d);
        Map<Integer, Double> distances = graph.findDistances(1);
        Map<Integer, Double> bfs = new MinimumWaysWithBFS<Integer>(graph).minimumWays(1);

        assertEquals(distances, bfs);
      //  assertEquals(result, distances);
    }

    @Test
    public void countOfMinWays(){
        NoOrientedWeightedGraph<Integer> graph = new NoOrientedWeightedGraph<>();
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

        Map<Integer, Integer> minWays =  graph.countOfMinimumWays(0);
        assertEquals(result, minWays);
    }

    @Test
    public void countOfMinWaysTest(){
        NoOrientedWeightedGraph<Integer> graph = new NoOrientedWeightedGraph<>();
        for (int i = 1; i <= 5; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 1);

        graph.addEdge(2, 3, 1);
        graph.addEdge(4, 3, 1);
        graph.addEdge(4, 5, 1);
        graph.addEdge(5, 3, 1);
        graph.addEdge(2, 5, 1);

        Map<Integer, Integer> result = new HashMap<>();
        result.put(1, 1);
        result.put(2, 1);
        result.put(4, 1);
        result.put(5, 2);
        result.put(3, 2);

        Map<Integer, Integer> minWays = graph.countOfMinimumWays(1);
        assertEquals(result, minWays);
    }
}
