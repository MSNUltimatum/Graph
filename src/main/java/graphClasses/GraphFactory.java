package graphClasses;

import abstractClasses.Graph;
import helpClasses.Edge;
import helpClasses.FlowEdge;

import java.io.FileNotFoundException;
import java.util.*;

public class GraphFactory<T> {
    public Graph<T> makeGraph(GraphType graphType){
        return switch (graphType) {
            case NOTORIENTED -> new NotOrientedGraph<T>();
            case NOTORIENTEDWEIGHTED -> new NoOrientedWeightedGraph<T>();
            case ORIENTED -> new OrientedGraph<T>();
            case ORIENTEDWEIGHTED -> new OrientedWeightedGraph<T>();
        };
    }

    public Graph<T> makeGraph(GraphType graphType, Graph<T> sourceGraph){
        return switch (graphType) {
            case NOTORIENTED -> new NotOrientedGraph<T>((NotOrientedGraph<T>)sourceGraph);
            case NOTORIENTEDWEIGHTED -> new NoOrientedWeightedGraph<T>((NoOrientedWeightedGraph<T>) sourceGraph);
            case ORIENTED -> new OrientedGraph<T>((OrientedGraph<T>) sourceGraph);
            case ORIENTEDWEIGHTED -> new OrientedWeightedGraph<T>((OrientedWeightedGraph<T>) sourceGraph);
        };
    }

    public Graph<T> makeGraph(GraphType graphType, String fileName, Class<T> tClass) throws FileNotFoundException {
        return switch (graphType) {
            case NOTORIENTED -> new NotOrientedGraph<T>(fileName, tClass);
            case NOTORIENTEDWEIGHTED -> new NoOrientedWeightedGraph<T>(fileName, tClass);
            case ORIENTED -> new OrientedGraph<T>(fileName, tClass);
            case ORIENTEDWEIGHTED -> new OrientedWeightedGraph<T>(fileName, tClass);
        };
    }

    public Graph<T> makeGraphWithoutEdgesBetweenSamePowerVertexes(Graph<T> sourceGraph){
        GraphType graphType = GraphType.getGraphType(sourceGraph);
        Graph<T> graph = makeGraph(graphType, sourceGraph);
        HashMap<T, HashMap<T, Double>> map = graph.getGraph();
        Map<T, Boolean> map1 = new HashMap<>();

        map.keySet().forEach(e -> map1.put(e, false));

        map.keySet().forEach(e -> {
            if(!map1.get(e)) {
                int powerOfe = map.get(e).size();
                Optional<T> first1 = map.keySet().stream()
                        .filter(l -> map.get(l).size() == powerOfe
                                && map.get(l).containsKey(e)
                                && !map1.get(l)).findFirst();

                if(first1.isPresent()) {
                    map1.put(first1.get(), true);
                    graph.deleteEdge(e, first1.get());
                    map1.put(e, true);
                }
            }
        });
        return graph;
    }

    public List<Edge> getEdgesListFromIntGraph(Graph<Integer> graph){
        List<Edge> edges = new ArrayList<>();
        graph.getGraph().forEach((key, val) -> {
            val.forEach((key1, val1) -> {
                Edge edge = new Edge();
                if(graph instanceof NoOrientedWeightedGraph || graph instanceof NotOrientedGraph) {
                    makePair(key, key1, edge);
                } else {
                    edge.setFirstValue(key);
                    edge.setSecondValue(key1);
                }
                edge.setWeight(val1);
                if(!edges.contains(edge)){
                    edges.add(edge);
                }
            });
        });
        return edges;
    }

    private void makePair(Integer key, Integer key1, Edge edge) {
        if(key >= key1){
            edge.setFirstValue(key1);
            edge.setSecondValue(key);
        } else {
            edge.setFirstValue(key);
            edge.setSecondValue(key1);
        }
    }

    public Map<Integer, List<FlowEdge>> makeFlowsGraph(OrientedWeightedGraph<Integer> graph){
        Map<Integer, List<FlowEdge>> flowGraph = new HashMap<>();
        graph.getGraph().keySet().forEach(e -> flowGraph.put(e, new ArrayList<>()));
        graph.getGraph().forEach((e, k) -> {
            k.forEach((k1, v1) -> {
                flowGraph.get(e).add(new FlowEdge(k1, flowGraph.get(k1).size(),v1));
                flowGraph.get(k1).add(new FlowEdge(e, flowGraph.get(e).size() - 1, 0d));
            });
        });
        return flowGraph;
    }
}
