import abstractClasses.Graph;
import graphClasses.GraphFactory;
import graphClasses.GraphType;
import graphClasses.OrientedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTests {
    GraphFactory<Integer> gf;
    OrientedGraph<Integer> orientedGraph;

    @BeforeEach
    public void init(){
        gf = new GraphFactory<>();
        orientedGraph = (OrientedGraph<Integer>) gf.makeGraph(GraphType.ORIENTED);
    }

    @Test
    public void testFindCommonIncomingVertexes(){
        setData();
        HashSet<Integer> target = new HashSet<>();
        target.add(1);
        target.add(4);
        assertEquals(orientedGraph.findCommonIncomingVertex(2, 3), target);
    }

    @Test
    public void testBadFindCommonIncomingVertexes(){
        setData();
        Assertions.assertThrows(RuntimeException.class, () -> orientedGraph.findCommonIncomingVertex(4, 5));
    }

    @Test
    public void testFindAllIncomingAndOutgoingVertex(){
        setData();
        HashSet<Integer> target = new HashSet<>();
        target.add(1);
        target.add(4);
        assertEquals(orientedGraph.findAllIncomingAndOutgoingVertex(2), target);
    }

    @Test
    public void testBadFindAllIncomingAndOutgoingVertex(){
        setData();
        Assertions.assertThrows(RuntimeException.class, () -> orientedGraph.findAllIncomingAndOutgoingVertex(3));
    }

    @Test
    public void checkTreeTest(){
        setDataToOrientGraph(orientedGraph, new int[]{1, 2, 3, 4, 5});
        orientedGraph.addEdge(1, 2);
        orientedGraph.addEdge(1, 3);
        orientedGraph.addEdge(2, 4);
        assertTrue(orientedGraph.CheckTree());
    }

    @Test
    public void checkTreeNoConnGraphTest(){
        setDataToOrientGraph(orientedGraph, new int[]{1, 2, 3, 4});
        orientedGraph.addEdge(1, 2);
        orientedGraph.addEdge(3, 4);
        assertFalse(orientedGraph.CheckTree());
    }

    @Test
    public void checkNoTreeTest(){
        OrientedGraph<Integer> maybeTree = (OrientedGraph<Integer>) gf.makeGraph(GraphType.ORIENTED);
        setDataToOrientGraph(maybeTree, new int[]{1, 2, 3, 4});
        maybeTree.addEdge(1, 2);
        maybeTree.addEdge(1, 3);
        maybeTree.addEdge(1, 4);
        maybeTree.addEdge(2, 4);
        maybeTree.addEdge(2, 3);
        maybeTree.addEdge(3, 4);
        assertFalse(maybeTree.CheckTree());
    }

    @Test
    public void checkTreeBadPowTest(){
        setDataToOrientGraph(orientedGraph, new int[]{1, 2, 3, 4});
        orientedGraph.addEdge(1, 2);
        orientedGraph.addEdge(1, 3);
        orientedGraph.addEdge(2, 4);
        orientedGraph.addEdge(3, 4);
        assertTrue(orientedGraph.CheckTree());
    }

    private void setDataToOrientGraph(Graph<Integer> g, int[] vertexes){
        Arrays.stream(vertexes).forEach(g::addVertex);
    }
    private void setData() {
        setDataToOrientGraph(orientedGraph, new int[] {1, 2, 3, 4, 5});
        orientedGraph.addEdge(1, 2);
        orientedGraph.addEdge(2, 1);
        orientedGraph.addEdge(3, 1);
        orientedGraph.addEdge(2, 4);
        orientedGraph.addEdge(3, 4);
        orientedGraph.addEdge(4, 2);
        orientedGraph.addEdge(4, 1);
    }
}
