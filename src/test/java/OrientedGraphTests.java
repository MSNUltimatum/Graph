import abstractClasses.Graph;
import abstractClasses.OrientedAbstractClass;
import graphClasses.GraphFactory;
import graphClasses.GraphType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class OrientedGraphTests {
    GraphFactory<Integer> gf = new GraphFactory<>();
    Graph<Integer> orientedGraph;
    Graph<Integer> notOrientedGraph;
    @BeforeEach
    public void initGraphs(){
        orientedGraph = gf.makeGraph(GraphType.ORIENTED);
        notOrientedGraph = gf.makeGraph(GraphType.NOTORIENTED);
        setVertexesAndEdgesOriented();
        setVertexesAndEdgesNonOriented();
    }

    private void setVertexesAndEdgesNonOriented() {
        notOrientedGraph.addVertex(1);
        notOrientedGraph.addVertex(2);
        notOrientedGraph.addVertex(-1);
        notOrientedGraph.addEdge(1, 1);
        notOrientedGraph.addEdge(1, 2);
    }

    private void setVertexesAndEdgesOriented() {
        orientedGraph.addVertex(1);
        orientedGraph.addVertex(2);
        orientedGraph.addVertex(-1);
        orientedGraph.addEdge(1, 1);
        orientedGraph.addEdge(1, 2);
        orientedGraph.addEdge(1, -1);
    }

    @Test
    public void testOrientedGraphAddVertex(){
        assertTrue(orientedGraph.hasVertex(1));
        assertTrue(orientedGraph.hasVertex(-1));
    }

    @Test
    public void testOrientedGraphAddEdges(){
        assertTrue(orientedGraph.hasEdge(1, 1));
        assertTrue(orientedGraph.hasEdge(1, -1));

        Assertions.assertThrows(RuntimeException.class, ()->{
            orientedGraph.addEdge(1, 2);
        });

        Assertions.assertThrows(RuntimeException.class, ()->{
            orientedGraph.addEdge(1, 2);
        });
    }

    @Test
    public void testFillGraphFromFile(){
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            Graph<Integer> graph = gf.makeGraph(GraphType.ORIENTED,"NoSuchDirectory", Integer.class);
        });
    }

    @Test
    public void testFillGraphFromWrongFile() throws FileNotFoundException {
        Graph<Integer> graph = gf.makeGraph(GraphType.ORIENTED,"inputgraph.txt", Integer.class);
        assertTrue(graph.hasEdge(1, 2));
    }

    @Test
    public void testCopyConstructor(){
        Graph<Integer> copyGraph = gf.makeGraph(GraphType.ORIENTED, orientedGraph);
        assertNotSame(copyGraph.getGraph(), orientedGraph.getGraph());
        assertEquals(copyGraph.getGraph(), orientedGraph.getGraph());
    }

    @Test
    public void testNotOrientedGraphAddVertexesAndEdges(){
        assertTrue(notOrientedGraph.hasVertex(1));
        assertTrue(notOrientedGraph.hasVertex(-1));
        assertTrue(notOrientedGraph.hasEdge(1, 1));
        assertTrue(notOrientedGraph.hasEdge(1, 2));
        assertTrue(notOrientedGraph.hasEdge(2, 1));
    }

    @Test
    public void testNotOrientedGraphDeleteVertexesAndEdges(){
        notOrientedGraph.deleteEdge(1, 2);
        assertFalse(notOrientedGraph.hasEdge(2, 1));
        assertFalse(notOrientedGraph.hasEdge(1, 2));

        notOrientedGraph.deleteVertex(2);

        assertFalse(notOrientedGraph.hasVertex(2));
    }

    @Test
    public void copyConstructorNonOriented(){
        Graph<Integer> copyGraph = gf.makeGraph(GraphType.NOTORIENTED, notOrientedGraph);
        assertNotSame(copyGraph.getGraph(), notOrientedGraph.getGraph());
        assertEquals(copyGraph.getGraph(), notOrientedGraph.getGraph());
    }

}
