package abstractClasses;

import graphClasses.DijkstraAlgorithmTasks;
import graphClasses.GraphType;
import interfaces.NonOrientedGraphBehavior;

import java.io.FileNotFoundException;
import java.util.*;

public abstract class NonOrientedAbstractGraph<T> extends Graph<T> implements NonOrientedGraphBehavior<T> {
    DijkstraAlgorithmTasks<T> dijkstraAlgorithmTasks = new DijkstraAlgorithmTasks<>(this);

    public NonOrientedAbstractGraph(){
        super();
    }
    public NonOrientedAbstractGraph(Graph<T> g){
        super(g);
    }
    public NonOrientedAbstractGraph(String fileName, Class<T> tClass) throws FileNotFoundException {
        super(fileName, tClass);
    }

    @Override
    public void deleteEdge(T from, T to) {
        if(hasVertex(from) && hasVertex(to) && hasEdge(from, to)){
            graph.get(from).remove(to);
            graph.get(to).remove(from);
        }
    }

    @Override
    public void deleteVertexWithEdges(T v) {
        if(hasVertex(v)){
            Set<T> ts = graph.get(v).keySet();
            Set<T> copySet = new HashSet<>(ts);
            copySet.forEach(e -> deleteEdge(v, e));
            graph.remove(v);
        }
    }

    @Override
    public Map<T, Integer> countOfMinimumWays(T u) {
        GraphType graphType = GraphType.getGraphType(this);
        if(graphType == GraphType.NOTORIENTED || graphType == GraphType.NOTORIENTEDWEIGHTED) {
            return dijkstraAlgorithmTasks.findCountOfMinWaysToAllVertexes(u);
        } else {
            throw new IllegalStateException("Graph is oriented");
        }
    }

    @Override
    public Map<T, Double> findDistances(T u) {
        GraphType graphType = GraphType.getGraphType(this);
        if(graphType == GraphType.NOTORIENTED || graphType == GraphType.NOTORIENTEDWEIGHTED) {
            return dijkstraAlgorithmTasks.findMinimumDistances(u);
        } else {
            throw new IllegalStateException("Graph is oriented");
        }
    }

    public Map<T, Double> BFS(T s) {
        LinkedList<T> queue = new LinkedList<>();
        Set<T> used = new HashSet<>();
        Map<T, Double> distances = new HashMap<>();
        Map<T, T> prev = new HashMap<>();
        prev.put(s, null);
        queue.add(s);
        used.add(s);
        distances.put(s, 0d);
        while (!queue.isEmpty()){
            T v = queue.poll();
            graph.get(v).forEach((k, d) -> {
                if (!used.contains(k)){
                    used.add(k);
                    queue.add(k);
                    prev.put(k, v);
                    distances.put(k, distances.get(v) + getWeightFromEdge(k, v));
                }
            });
        }
        System.out.println();
        graph.keySet().forEach(e -> {
            List<T> path = new ArrayList<>();
            T a = e;
            while (a != null){
                path.add(a);
                a = prev.get(a);
            }
            Collections.reverse(path);
            path.forEach(l -> System.out.printf("%s ",l));
            System.out.println();
        });
        return distances;
    }

}
