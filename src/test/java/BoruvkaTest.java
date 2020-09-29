import abstractClasses.Graph;
import abstractClasses.NonOrientedAbstractGraph;
import graphClasses.BoruvkaAlgorithm;
import graphClasses.GraphFactory;
import graphClasses.GraphType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
public class BoruvkaTest {
    @Test
    public void boruvkaTest(){
        Graph<Integer> noOrientGraph = new GraphFactory<Integer>().makeGraph(GraphType.NOTORIENTEDWEIGHTED);
        noOrientGraph.addVertex(0);
        noOrientGraph.addVertex(1);
        noOrientGraph.addVertex(2);
        noOrientGraph.addVertex(3);
        noOrientGraph.addVertex(4);
        noOrientGraph.addEdge(0, 1, 5.2);
        noOrientGraph.addEdge(0, 3, 7.1);
        noOrientGraph.addEdge(1, 3, 5.9);
        noOrientGraph.addEdge(1, 4, 3.4);
        noOrientGraph.addEdge(2, 1, 1.5);
        noOrientGraph.addEdge(2, 3, 2.3);
        noOrientGraph.addEdge(3, 4, 8.5);
        noOrientGraph.addEdge(4, 2, 2.7);
        BoruvkaAlgorithm<Integer> boruvkaAlgorithm = new BoruvkaAlgorithm<Integer>(noOrientGraph);
        boruvkaAlgorithm.Boruvka_Mst();
    }
}
