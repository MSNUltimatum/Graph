package graphClasses;

import abstractClasses.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoruvkaAlgorithm<T> {
    private static class Pair<T>{
        private T firstValue;
        private T secondValue;

        public T getFirstValue() {
            return firstValue;
        }

        public void setFirstValue(T firstValue) {
            this.firstValue = firstValue;
        }

        public T getSecondValue() {
            return secondValue;
        }

        public void setSecondValue(T secondValue) {
            this.secondValue = secondValue;
        }
    }
    private final Graph<T> noOrientedWeightedGraph;

    public BoruvkaAlgorithm(Graph<T> graph){
        this.noOrientedWeightedGraph = graph;
    }

    public void Boruvka_Mst(){
        int v = noOrientedWeightedGraph.getVertexesCount();
        Graph<T> mst = new GraphFactory<T>().makeGraph(GraphType.NOTORIENTEDWEIGHTED);
        noOrientedWeightedGraph.getGraph().keySet().forEach(mst::addVertex);
        while (!mst.isConnected()){
            noOrientedWeightedGraph.getGraph().keySet().forEach(e -> {
                if(mst.countReachableNodes(e) < v){
                    HashMap<T, Double> neighbours = noOrientedWeightedGraph.getGraph().get(e);
                    for (Map.Entry<T, Double> entry: neighbours.entrySet()) {
                        if(!mst.hasEdge(e, entry.getKey()) && !mst.reachable(e, entry.getKey())){
                            mst.addEdge(e, entry.getKey(), entry.getValue());
                            break;
                        }
                    }
                }
            });
        }
        mst.printGraphToConsole();
    }

}

