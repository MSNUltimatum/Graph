import graphClasses.DinicAlgorithm;
import graphClasses.OrientedWeightedGraph;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DinicTest {
    @Test
    public void DinicAlgorithmTest(){
        OrientedWeightedGraph<Integer> orientedWeightedGraph = new OrientedWeightedGraph<>();
        for (int i = 0; i <= 5; i++) {
            orientedWeightedGraph.addVertex(i);
        }
        orientedWeightedGraph.addEdge(0, 1, 10);
        orientedWeightedGraph.addEdge(1, 3, 4);
        orientedWeightedGraph.addEdge(3, 5, 10);
        orientedWeightedGraph.addEdge(1, 2, 2);
        orientedWeightedGraph.addEdge(0, 2, 10);
        orientedWeightedGraph.addEdge(1, 4, 8);
        orientedWeightedGraph.addEdge(2, 4, 9);
        orientedWeightedGraph.addEdge(4, 3, 6);
        orientedWeightedGraph.addEdge(4, 5, 10);
        DinicAlgorithm dinicAlgorithm = new DinicAlgorithm(0, 5, orientedWeightedGraph);
        double v = dinicAlgorithm.maxFlow();
        Assertions.assertEquals(19, v);
    }

    @Test
    public void DinicAlgorithmTest2(){
        OrientedWeightedGraph<Integer> orientedWeightedGraph = new OrientedWeightedGraph<>();
        for (int i = 0; i <= 6; i++) {
            orientedWeightedGraph.addVertex(i);
        }
        orientedWeightedGraph.addEdge(0, 1, 1);
        orientedWeightedGraph.addEdge(1, 6, 2);
        orientedWeightedGraph.addEdge(0, 2, 1);
        orientedWeightedGraph.addEdge(0, 3, 2);
        orientedWeightedGraph.addEdge(2, 5, 2);
        orientedWeightedGraph.addEdge(2, 4, 1);
        orientedWeightedGraph.addEdge(2, 3, 1);
        orientedWeightedGraph.addEdge(3, 4, 2);
        orientedWeightedGraph.addEdge(4, 2, 1);
        orientedWeightedGraph.addEdge(4, 6, 1);
        orientedWeightedGraph.addEdge(5, 4, 2);
        orientedWeightedGraph.addEdge(5, 6, 2);
        DinicAlgorithm dinicAlgorithm = new DinicAlgorithm(0, 6, orientedWeightedGraph);
        double v = dinicAlgorithm.maxFlow();
        Assertions.assertEquals(4, v);
    }
}
