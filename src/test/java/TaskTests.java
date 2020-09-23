import graphClasses.OrientedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.Assert.*;
public class TaskTests {
    OrientedGraph<Integer> orientedGraph;

    @BeforeEach
    public void init(){
        orientedGraph = new OrientedGraph<>();
        setData();
    }

    @Test
    public void testFindCommonIncomingVertexes(){
        HashSet<Integer> target = new HashSet<>();
        target.add(1);
        target.add(4);
        assertEquals(orientedGraph.findCommonIncomingVertex(2, 3), target);
    }

    @Test
    public void testBadFindCommonIncomingVertexes(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            orientedGraph.findCommonIncomingVertex(4, 5);
        });
    }

    @Test
    public void testFindAllIncomingAndOutgoingVertex(){
        HashSet<Integer> target = new HashSet<>();
        target.add(1);
        target.add(4);
        assertEquals(orientedGraph.findAllIncomingAndOutgoingVertex(2), target);
    }

    @Test
    public void testBadFindAllIncomingAndOutgoingVertex(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            orientedGraph.findAllIncomingAndOutgoingVertex(3);
        });
    }

    @Test
    public void testGraphFactory(){
        GraphFactory<Integer> gf = new GraphFactory<>();
        gf.makeGraphWithoutEdgesBetweenSamePowerVertexes(orientedGraph);
    }

    private void setData() {
        orientedGraph.addVertex(1);
        orientedGraph.addVertex(2);
        orientedGraph.addVertex(3);
        orientedGraph.addVertex(4);
        orientedGraph.addVertex(5);
        orientedGraph.addEdge(1, 2);
        orientedGraph.addEdge(2, 1);
        orientedGraph.addEdge(3, 1);
        orientedGraph.addEdge(2, 4);
        orientedGraph.addEdge(3, 4);
        orientedGraph.addEdge(4, 2);
        orientedGraph.addEdge(4, 1);
    }
}
