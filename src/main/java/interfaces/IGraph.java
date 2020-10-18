package interfaces;

import java.util.HashMap;

public interface IGraph<T> {
    void addVertex(T name);

    boolean hasVertex(T name);

    boolean hasEdge(T from, T to);

    void deleteVertex(T name);

    void deleteEdge(T from, T to);

    void printGraphToFile(String fileName);

    int getVertexesCount();

    void deleteVertexWithEdges(T v);
}
