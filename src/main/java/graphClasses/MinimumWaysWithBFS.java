package graphClasses;

import helpClasses.Edge;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumWaysWithBFS<T> {
    private NoOrientedWeightedGraph<Integer> graph;
    private List<Edge> edgesToSwap;
    private Set<Integer> newVertexes;

    public MinimumWaysWithBFS(NoOrientedWeightedGraph<Integer> noOrientedWeightedGraph) {
        this.graph = makeGraphWithIntermediateVertex(noOrientedWeightedGraph);
    }

    public Map<Integer, Double> minimumWays(Integer s) {
        LinkedList<Integer> queue = new LinkedList<>();
        Set<Integer> used = new HashSet<>();
        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();
        prev.put(s, null);
        queue.add(s);
        used.add(s);
        distances.put(s, 0d);
        BFSCycle(queue, used, distances, prev);
        Map<Integer, Double> collect = refactorDistances(distances);
        printMinimumWays(prev);
        return collect;
    }

    private void BFSCycle(LinkedList<Integer> queue, Set<Integer> used, Map<Integer, Double> distances, Map<Integer, Integer> prev) {
        while (!queue.isEmpty()){
            Integer v = queue.poll();
            graph.getGraph().get(v).forEach((k, d) -> {
                if (!used.contains(k)){
                    used.add(k);
                    queue.add(k);
                    prev.put(k, v);
                    distances.put(k, distances.get(v) + graph.getWeightFromEdge(k, v));
                }
            });
        }
    }

    private Map<Integer, Double> refactorDistances(Map<Integer, Double> distances) {
        Map<Integer, Double> collect = distances.entrySet().stream()
                                            .filter(e -> !newVertexes.contains(e.getKey()))
                                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return collect;
    }

    private void printMinimumWays(Map<Integer, Integer> prev) {
        for (Integer e : graph.getGraph().keySet()) {
            if (!newVertexes.contains(e)) {
                List<Integer> path = new ArrayList<>();
                Integer a = e;
                while (a != null) {
                    if(!newVertexes.contains(a)) {
                        path.add(a);
                    }
                    a = prev.get(a);
                }

                Collections.reverse(path);
                path.forEach(l -> System.out.printf("%s ", l));
                System.out.println();
            }
        }
    }

    private NoOrientedWeightedGraph<Integer> makeGraphWithIntermediateVertex(NoOrientedWeightedGraph<Integer> graph){
        NoOrientedWeightedGraph<Integer> newGraph = new NoOrientedWeightedGraph<>(graph);
        edgesToSwap = new ArrayList<>();
        newVertexes = new HashSet<>();
        findEdgesToRefactoring(newGraph);
        refactorGraphWithNewEdges(newGraph);

        return newGraph;
    }

    private void findEdgesToRefactoring(NoOrientedWeightedGraph<Integer> newGraph) {
        for (Integer e: newGraph.getGraph().keySet()) {
            for (Map.Entry<Integer, Double> entry:
                    newGraph.getGraph().get(e).entrySet()) {
                if(entry.getValue() == 1){
                    edgesToSwap.add(new Edge(e, entry.getKey(), 1d));
                }
            }
        }
    }

    private void refactorGraphWithNewEdges(NoOrientedWeightedGraph<Integer> newGraph) {
        for (Edge edge:
                edgesToSwap) {
            newGraph.deleteEdge(edge.getFirstValue(), edge.getSecondValue());
            Integer vertex = generateVertex(newGraph);
            newVertexes.add(vertex);
            newGraph.addVertex(vertex);
            newGraph.addEdge(edge.getFirstValue(), vertex , 0.5);
            newGraph.addEdge(vertex, edge.getSecondValue(), 0.5);
        }
    }

    private Integer generateVertex(NoOrientedWeightedGraph<Integer> graph) {
        Integer max = Collections.max(graph.getGraph().keySet());
        return IntStream.range(0, max + 2)
                .filter(e -> !graph.getGraph().containsKey(e))
                .findFirst()
                .orElseThrow();
    }
}
