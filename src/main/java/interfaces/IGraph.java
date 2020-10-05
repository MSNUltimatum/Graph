package interfaces;

public interface IGraph<T> {
    void addVertex(T name);

    boolean hasVertex(T name);

    void addEdge(T fromV, T toV);

    void addEdge(T fromV, T toV, double weight);

    boolean hasEdge(T from, T to);

    void deleteVertex(T name);

    void deleteEdge(T from, T to);

    void printGraphToFile(String fileName);

    double getWeightFromEdge(T fromV, T toV);

    void setWeightToEdge(T fromV, T toV, double weight);

    int getVertexesCount();

    void deleteVertexWithEdges(T v);

    boolean isConnected();

    int countReachableNodes(T u);
}
