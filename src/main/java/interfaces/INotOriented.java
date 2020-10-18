package interfaces;

import java.util.Map;

public interface INotOriented<T> {

    Map<T, Double> findDistances(T u);

    Map<T, Integer> countOfMinimumWays(T u);
}
