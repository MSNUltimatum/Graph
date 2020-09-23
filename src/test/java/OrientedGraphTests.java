import graphClasses.NotOrientedGraph;
import graphClasses.OrientedGraph;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class OrientedGraphTests {
    OrientedGraph<Integer> orientedGraph;
    NotOrientedGraph<Integer> notOrientedGraph;
    @BeforeEach
    public void initGraphs(){
        orientedGraph = new OrientedGraph<>();
        notOrientedGraph = new NotOrientedGraph<>();
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
        try {
            orientedGraph.addEdge(1, 2);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Existed edge (1, 2)");
        }

        try {
            orientedGraph.addEdge(1, 23);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Bad vertexes");
        }
    }

    @Test
    public void testFillGraphFromFile(){
        try {
            OrientedGraph<Integer> graph = new OrientedGraph<Integer>("NoSuchDirectory", Integer.class);
        } catch (FileNotFoundException e) {
            assertEquals(e.getMessage(), "No such file");
        }
    }

    @Test
    public void testFillGraphFromWrongFile(){
        try {
            OrientedGraph<Integer> graph = new OrientedGraph<Integer>("inputgraph.txt", Integer.class);
            assertTrue(graph.hasEdge(1, 2));
        } catch (FileNotFoundException ignored) {
        }
    }

    @Test
    public void testCopyConstructor(){
        OrientedGraph<Integer> copyGraph = new OrientedGraph<>(orientedGraph);
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
        NotOrientedGraph<Integer> copyGraph = new NotOrientedGraph<>(notOrientedGraph);
        assertNotSame(copyGraph.getGraph(), notOrientedGraph.getGraph());
        assertEquals(copyGraph.getGraph(), notOrientedGraph.getGraph());
    }
}
