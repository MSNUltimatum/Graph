package graphClasses;

import abstractClasses.Graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FloydWarshallTask {
    int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
    int vNum; // количество вершин
    Graph<Integer> copyOfSourceGraph;

    public FloydWarshallTask(Graph<Integer> graph) {
        copyOfSourceGraph = new GraphFactory<Integer>().makeGraph(GraphType.NOTORIENTEDWEIGHTED, graph);
        vNum = graph.getVertexesCount();
    }

    public List<Integer> findCenterOfGraph(){
        Map<Integer, Double> eccentricity = findEccentricity();
        Double radius = Collections.min(eccentricity.values());
        return eccentricity.keySet().stream()
                .filter(e -> eccentricity.get(e).equals(radius))
                .collect(Collectors.toList());
    }

    private Map<Integer, Double> findEccentricity(){
        floydWarshall();
        Map<Integer, Double> eccentricities = new HashMap<>();
        copyOfSourceGraph.getGraph().forEach((k, v) -> {
            eccentricities.put(k, Collections.max(v.values()));
        });
        return eccentricities;
    }

    private void floydWarshall() {
        copyOfSourceGraph.getGraph().keySet().forEach(e -> {
            copyOfSourceGraph.getGraph().forEach((k, v) -> {
                if(!v.containsKey(e)){
                    v.put(e, Double.MAX_VALUE);
                }
            });
        });

        for (int k = 0; k < vNum; k++) {
            for (int i = 0; i < vNum; i++) {
                for (int j = 0; j < vNum; j++) {
                    copyOfSourceGraph.setWeightToEdge(i, j, Math.min(copyOfSourceGraph.getWeightFromEdge(i, j),
                            copyOfSourceGraph.getWeightFromEdge(i, k) + copyOfSourceGraph.getWeightFromEdge(j, k)));
                }
            }
        }
    }
}
