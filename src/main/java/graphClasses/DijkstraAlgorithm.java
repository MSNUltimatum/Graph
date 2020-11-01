package graphClasses;

import abstractClasses.Graph;

import java.util.*;

public class DijkstraAlgorithm<T> {
    private Map<T, Integer> countOfMinimumWays;
    private Map<T, T> prev;
    private Graph<T> sourceGraph;

    public DijkstraAlgorithm(Graph<T> sourceGraph) {
        this.sourceGraph = sourceGraph;
    }

    public Map<T, Double> findMinimumDistances(T u) {
        Set<T> used = new HashSet<>();
        Map<T, Double> distance = new HashMap<>();

        initializeCollections(u, distance);
        dijkstraAlgorithm(used, distance);
        findAndPrintPath();
        return distance;
    }

    public Map<T, Integer> findCountOfMinWaysToAllVertexes(T u){
        Set<T> used = new HashSet<>();
        Map<T, Double> distance = new HashMap<>();

        initializeCollections(u, distance);
        dijkstraAlgorithm(used, distance);
        return countOfMinimumWays;
    }

    private void initializeCollections(T u, Map<T, Double> distance) {
        countOfMinimumWays  = new HashMap<>();
        prev = new HashMap<>();
        sourceGraph.getGraph().keySet().forEach(e -> {
            distance.put(e, Double.MAX_VALUE);
            countOfMinimumWays.put(e, 0);
        });
        countOfMinimumWays.put(u, 1);
        distance.put(u, 0.0);
        prev.put(u, null);
    }

    private void dijkstraAlgorithm(Set<T> used, Map<T, Double> distance) {
        for(;;){
            T v = null;
            for (T e: sourceGraph.getGraph().keySet()) {
                if(!used.contains(e) && distance.get(e) < Double.MAX_VALUE
                        && (v == null || distance.get(v) > distance.get(e))){
                    v = e;
                }
            }
            if(v == null)
                break;
            used.add(v);
            for (T e: sourceGraph.getGraph().keySet()) {
                if (!used.contains(e) && sourceGraph.getGraph().get(v).containsKey(e)) {
                    double min = Math.min(distance.get(e), distance.get(v) + sourceGraph.getGraph().get(v).get(e));
                    if(min != distance.get(e)) {
                        prev.put(e, v);
                        countOfMinimumWays.put(e, countOfMinimumWays.get(v));
                    } else if(distance.get(v) + sourceGraph.getGraph().get(v).get(e) == distance.get(e) && v != prev.get(e)) {
                        countOfMinimumWays.put(e, countOfMinimumWays.get(e) + countOfMinimumWays.get(v));
                    }
                    distance.put(e, min);
                }
            }
        }
    }

    private void findAndPrintPath() {
        sourceGraph.getGraph().keySet().forEach(e -> {
            List<T> path = new ArrayList<>();
            T a = e;
            while (a != null){
                path.add(a);
                a = prev.get(a);
            }
            Collections.reverse(path);
        });
    }
}
