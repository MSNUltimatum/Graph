package interfaces;

public interface IGraph<T> {
    void addVertex(T name);

    boolean hasVertex(T name);

    void addEdge(T fromV, T toV);

    void addEdge(T fromV, T toV, int weight);

    boolean hasEdge(T from, T to);

    void deleteVertex(T name);

    void deleteEdge(T from, T to);

    void printGraphToFile(String fileName);

    int getWeightFromEdge(T fromV, T toV);

    void setWeightToEdge(T fromV, T toV, int weight);

}
