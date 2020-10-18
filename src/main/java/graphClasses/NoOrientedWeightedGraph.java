package graphClasses;

import abstractClasses.NoOrientedAbstractGraph;
import interfaces.INotOriented;
import interfaces.IWeighted;

import java.io.FileNotFoundException;
import java.util.*;

public class NoOrientedWeightedGraph<T> extends NoOrientedAbstractGraph<T> implements IWeighted<T>, INotOriented<T> {
    public NoOrientedWeightedGraph() {
        super();
    }

    public NoOrientedWeightedGraph(NoOrientedWeightedGraph<T> g) {
        super(g);
    }

    public NoOrientedWeightedGraph(String fileName, Class<T> tClass) throws FileNotFoundException {
        super(fileName, tClass);
    }

    @Override
    public void addEdge(T fromV, T toV, double weight) {
        if(hasVertex(fromV) && hasVertex(toV) && !hasEdge(fromV, toV)){
            graph.get(fromV).put(toV, weight);
            graph.get(toV).put(fromV, weight);
        } else if(hasEdge(fromV, toV) && getWeightFromEdge(fromV, toV) != weight){
            changeWeight(fromV, toV, weight);
        } else {
            throw new RuntimeException("Bad data to insert");
        }
    }

    @Override
    public double getWeightFromEdge(T fromV, T toV) {
        if(hasEdge(fromV, toV)) {
            return graph.get(fromV).get(toV);
        } else {
            throw new IllegalStateException("Bad data to get");
        }
    }

    @Override
    public void setWeightToEdge(T fromV, T toV, double weight) {
        if(hasEdge(fromV, toV)) {
            graph.get(fromV).put(toV, weight);
        } else {
            throw new IllegalStateException("Bad data to change");
        }
    }

    public Map<T, Double> minimumWays(T s) {
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

    @Override
    protected void fillWeightedGraph(List<String[]> lines, Class<T> tClass) {
        lines.forEach(line ->{
            for (int i = 1; i < line.length; i++) {
                addEdge(castArg(tClass, line[0]), castArg(tClass, line[i].split("=")[0]), Double.parseDouble(line[i].split("=")[1]));
            }
        });
    }
}
