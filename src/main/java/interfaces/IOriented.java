package interfaces;

import java.util.Set;

public interface IOriented<T> {
    Set<T> findCommonIncomingVertex(T v, T u);

    Set<T> findAllIncomingAndOutgoingVertex(T u);

    boolean CheckTree();

    int getEdgesCountInOrientedGraph();
}
