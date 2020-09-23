import abstractClasses.NonOrientedAbstractGraph;
import graphClasses.NotOrientedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
public class GraphFactoryTests {
    @Test
    public void testGraphFactory(){
        GraphFactory<Integer> gf = new GraphFactory<>();
        NotOrientedGraph<Integer> graph;
        NotOrientedGraph<Integer> graph1 = new NotOrientedGraph<>();
        setData(graph1);

        graph = (NotOrientedGraph<Integer>) gf.makeGraphWithoutEdgesBetweenSamePowerVertexes(graph1);

        graph1.deleteEdge(1, 2);
        graph1.deleteEdge(3, 4);

        assertEquals(graph1.getGraph(), graph.getGraph());
    }

    private void setData(NonOrientedAbstractGraph<Integer> graph){
        graph.addVertex(1); //1 || 2 3 4
        graph.addVertex(2); //2 || 1 4 3
        graph.addVertex(3); //2 || 1 4 2
        graph.addVertex(4); //2 || 2 1 3
        graph.addVertex(5); // 0
        graph.addEdge(1, 2);
        graph.addEdge(3, 1);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
    }
}
