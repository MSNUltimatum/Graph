package interfaces;

import java.util.Map;

public interface NonOrientedGraphBehavior <T> extends IGraph<T> {
    Map<T, Double> findDistances(T u);
}
