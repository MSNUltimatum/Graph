import graphClasses.*;
import org.junit.jupiter.api.Test;

public class BoruvkaTest {
    @Test
    public void boruvkaTest(){
        NoOrientedWeightedGraph<Integer> noOrientGraph = new NoOrientedWeightedGraph<>();
        noOrientGraph.addVertex(0);
        noOrientGraph.addVertex(1);
        noOrientGraph.addVertex(2);
        noOrientGraph.addVertex(3);
        noOrientGraph.addVertex(4);

        noOrientGraph.addEdge(0, 1, 8);
        noOrientGraph.addEdge(0, 2, 5);
        noOrientGraph.addEdge(1, 2, 9);
        noOrientGraph.addEdge(1, 3, 11);
        noOrientGraph.addEdge(2, 3, 15);
        noOrientGraph.addEdge(3, 4, 7);
        noOrientGraph.addEdge(2, 4, 10);
        BoruvkaAlgorithm boruvkaAlgorithm = new BoruvkaAlgorithm(noOrientGraph);
        boruvkaAlgorithm.Boruvka_Mst();
    }

    @Test
    public void boruvkaForestTest(){
        NoOrientedWeightedGraph<Integer> noOrientGraph = new NoOrientedWeightedGraph<>();
        noOrientGraph.addVertex(0);
        noOrientGraph.addVertex(1);
        noOrientGraph.addVertex(2);
        noOrientGraph.addVertex(3);
        noOrientGraph.addVertex(4);
        noOrientGraph.addVertex(5);
        noOrientGraph.addVertex(6);

        noOrientGraph.addEdge(0, 1, 1);
        noOrientGraph.addEdge(0, 2, 5);
        noOrientGraph.addEdge(1, 2, 3);
        noOrientGraph.addEdge(3, 4, 7);
        noOrientGraph.addEdge(3, 5, 10);
        noOrientGraph.addEdge(4, 5, 15);
        BoruvkaAlgorithm boruvkaAlgorithm = new BoruvkaAlgorithm(noOrientGraph);
        boruvkaAlgorithm.Boruvka_Mst();
    }
}
