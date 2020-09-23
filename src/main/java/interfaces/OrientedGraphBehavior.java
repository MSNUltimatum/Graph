package interfaces;

import java.util.Set;

public interface OrientedGraphBehavior<T> extends IGraph<T> {
    Set<T> findCommonIncomingVertex(T v, T u);

    Set<T> findAllIncomingAndOutgoingVertex(T u);
}
