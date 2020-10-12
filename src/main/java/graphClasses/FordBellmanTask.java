package graphClasses;

import abstractClasses.Graph;
import helpClasses.Pair;

import java.util.*;

public class FordBellmanTask {
    private final int size;
    private final List<Pair> edges;
    private final Graph<Integer> graph;

    public FordBellmanTask(Graph<Integer> graph) {
        this.graph = graph;
        size = graph.getVertexesCount();
        edges = new GraphFactory<>().getEdgesListFromIntGraph(graph);
    }

    public List<Integer> getNegateCycle() {
        Map<Integer, Double> d = new HashMap<>();
        Map<Integer, Integer> p = new HashMap<>();
        graph.getGraph().keySet().forEach(e -> {
            d.put(e, 0d);
            p.put(e, -1);
        });

        int x = 0;
        for (int i = 0; i < size; ++i) {
            x = -1;
            for (Pair edge : edges) {
                if (d.get(edge.getSecondValue()) > d.get(edge.getFirstValue()) + edge.getWeight()) {
                    d.put(edge.getSecondValue(),
                            Math.max(-Double.MAX_VALUE, d.get(edge.getFirstValue()) + edge.getWeight()));
                    p.put(edge.getSecondValue(), edge.getFirstValue());
                    x = edge.getSecondValue();
                }
            }
        }
        if (x == -1) {
            throw new RuntimeException("No negate cycles");
        } else {
            int y = x;
            for (int i=0; i < size; ++i)
                y = p.get(y);

            List<Integer> path = new ArrayList<>();
            for (int cur = y; ; cur = p.get(cur)) {
                path.add (cur);
                if (cur == y && path.size() > 1)
                    break;
            }
            Collections.reverse(path);
            System.out.println("Negative cycle: ");
            path.forEach(e -> System.out.printf("%s ", e));
            return path;
        }
    }
}
