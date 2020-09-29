package abstractClasses;

import interfaces.NonOrientedGraphBehavior;

import java.io.FileNotFoundException;
import java.util.*;

public abstract class NonOrientedAbstractGraph<T> extends Graph<T> implements NonOrientedGraphBehavior<T> {
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
    public Map<T, Double> findDistances(T u) {
        Set<T> used = new HashSet<>();
        Map<T, Double> distance = new HashMap<>();
        Map<T, T> prev = new HashMap<>();
        graph.keySet().forEach(e -> distance.put(e, Double.MAX_VALUE));
        distance.put(u, 0.0);
        prev.put(u, null);

        for(;;){
            T v = null;
            for (T e: graph.keySet()) {
                if(!used.contains(e) && distance.get(e) < Double.MAX_VALUE && (v == null || distance.get(v) > distance.get(e))){
                    v = e;
                }
            }
            if(v == null)
                break;
            used.add(v);
            for (T e: graph.keySet()) {
                if (!used.contains(e) && graph.get(v).containsKey(e)) {
                    double min = Math.min(distance.get(e), distance.get(v) + graph.get(v).get(e));
                    if(min != distance.get(e))
                        prev.put(e, v);
                    distance.put(e, min);
                }
            }
        }

        findAndPrintPath(prev);
        distance.entrySet().forEach(System.out::println);
        return distance;
    }

    private void findAndPrintPath(Map<T, T> prev) {
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
    }
}
