package graphClasses;

import helpClasses.FlowEdge;

import java.util.*;

public class DinicAlgorithm {
    private final Map<Integer, List<FlowEdge>> graph;
    private final Integer sourceVertex;
    private final Integer targetVertex;
    private final Map<Integer, Double> dist = new HashMap<>();
    private final Map<Integer, Integer> ptr = new HashMap<>();
    private final Set<Integer> keySet;

    public DinicAlgorithm(Integer sourceVertex, Integer targetVertex, OrientedWeightedGraph<Integer> orientedWeightedGraph) {
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
        keySet = orientedWeightedGraph.getGraph().keySet();
        graph = new GraphFactory<Integer>().makeFlowsGraph(orientedWeightedGraph);
    }

    public double maxFlow() {
        double flow = 0;
        for (;;) {
            if (!dinicBfs())  break;
            keySet.forEach(e -> ptr.put(e, 0));
            double pushed;
            do {
                pushed = dinicDfs(sourceVertex, Double.MAX_VALUE);
                if(pushed == 0)
                    break;
                flow += pushed;
            } while (pushed != 0);
        }
        return flow;
    }

    boolean dinicBfs() {
        keySet.forEach(e -> dist.put(e, -1d));
        dist.put(sourceVertex, 0d);
        Map<Integer, Integer> Q = new HashMap<>();
        int sizeQ = 0;
        Q.put(sizeQ++, sourceVertex);
        for (int i = 0; i < sizeQ; i++) {
            int u = Q.get(i);
            for (FlowEdge e : graph.get(u)) {
                if (dist.get(e.getTarget()) < 0 && e.getFlow() < e.getCapacity()) {
                    dist.put(e.getTarget(), dist.get(u) + 1);
                    Q.put(sizeQ++, e.getTarget());
                }
            }
        }
        return dist.get(targetVertex) >= 0;
    }

    double dinicDfs(int u, double f) {
        if (u == targetVertex)
            return f;
        for (; ptr.get(u) < graph.get(u).size();  ptr.put(u, ptr.get(u) + 1)) {
            FlowEdge e = graph.get(u).get(ptr.get(u));
            if (dist.get(e.getTarget()) == dist.get(u) + 1 && e.getFlow() < e.getCapacity()) {
                double df = dinicDfs(e.getTarget(), Math.min(f, e.getCapacity() - e.getFlow()));
                if (df > 0) {
                    e.setFlow(e.getFlow() + df);
                    graph.get(e.getTarget()).get(e.getReversed())
                            .setFlow(graph.get(e.getTarget()).get(e.getReversed()).getFlow() - df);
                    return df;
                }
            }
        }
        return 0;
    }
}
