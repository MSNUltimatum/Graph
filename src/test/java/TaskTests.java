import abstractClasses.Graph;
import abstractClasses.OrientedAbstractClass;
import graphClasses.GraphFactory;
import graphClasses.GraphType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
public class TaskTests {
    GraphFactory<Integer> gf;
    Graph<Integer> orientedGraph;

    @BeforeEach
    public void init(){
        gf = new GraphFactory<>();
        orientedGraph = gf.makeGraph(GraphType.ORIENTED);
        setData();
    }

    @Test
    public void testFindCommonIncomingVertexes(){
        HashSet<Integer> target = new HashSet<>();
        target.add(1);
        target.add(4);
        assertEquals(((OrientedAbstractClass)orientedGraph).findCommonIncomingVertex(2, 3), target);
    }

    @Test
    public void testBadFindCommonIncomingVertexes(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            ((OrientedAbstractClass)orientedGraph).findCommonIncomingVertex(4, 5);
        });
    }

    @Test
    public void testFindAllIncomingAndOutgoingVertex(){
        HashSet<Integer> target = new HashSet<>();
        target.add(1);
        target.add(4);
        assertEquals(((OrientedAbstractClass)orientedGraph).findAllIncomingAndOutgoingVertex(2), target);
    }

    @Test
    public void testBadFindAllIncomingAndOutgoingVertex(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            ((OrientedAbstractClass)orientedGraph).findAllIncomingAndOutgoingVertex(3);
        });
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
