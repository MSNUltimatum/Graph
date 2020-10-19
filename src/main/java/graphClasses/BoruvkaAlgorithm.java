package graphClasses;

import abstractClasses.Graph;
import helpClasses.Edge;
import helpClasses.UnionFind;

import java.util.*;

public class BoruvkaAlgorithm {
    private final NoOrientedWeightedGraph<Integer> noOrientedWeightedGraph;
    private final NoOrientedWeightedGraph<Integer> mst;
    private final UnionFind uf;
    private int totalWeight = 0;
    private final List<Edge> edges;
    private int mstEdgesSize = 0;
    private Edge[] closestEdgeArray;


    public BoruvkaAlgorithm(NoOrientedWeightedGraph<Integer> graph) {
        this.noOrientedWeightedGraph = graph;
        uf = new UnionFind(graph.getVertexesCount());
        mst = new NoOrientedWeightedGraph<>();
        noOrientedWeightedGraph.getGraph().keySet().forEach(mst::addVertex);
        edges = new GraphFactory<>().getEdgesListFromIntGraph(graph);
    }

    public Graph<Integer> Boruvka_Mst() {
        int size = noOrientedWeightedGraph.getVertexesCount();
        for (int t = 1; t < size && mstEdgesSize < size - 1; t = t + t) {
            closestEdgeArray = new Edge[size];
            fillClosestArray();
            treeMaker(size);
        }
        mst.printGraphToConsole();
        System.out.println(totalWeight);
        return mst;
    }

    private void treeMaker(int size) {
        for (int i = 0; i < size; i++) {
            Edge edge = closestEdgeArray[i];
            if (edge != null) {
                int u = edge.getFirstValue();
                int v = edge.getSecondValue();
                double weight = getvParentWeight(u, v);
                if (!uf.find(u).equals(uf.find(v))) {
                    mst.addEdge(u, v, weight);
                    totalWeight += weight;
                    uf.union(u, v);
                    mstEdgesSize += 1;
                }
            }
        }
    }

    private void fillClosestArray() {
        for (Edge edge : edges) {
            Integer u = edge.getFirstValue();
            Integer v = edge.getSecondValue();
            int uParent = uf.find(u);
            int vParent = uf.find(v);

            if (uParent == vParent) {
                continue;
            }

            double weight = getvParentWeight(u, v);

            if (closestEdgeArray[uParent] == null) {
                closestEdgeArray[uParent] = edge;
            }
            if (closestEdgeArray[vParent] == null) {
                closestEdgeArray[vParent] = edge;
            }

            double uParentWeight = getvParentWeight(closestEdgeArray[uParent].getFirstValue(), closestEdgeArray[uParent].getSecondValue());
            double vParentWeight = getvParentWeight(closestEdgeArray[vParent].getFirstValue(), closestEdgeArray[vParent].getSecondValue());

            if (weight < uParentWeight) {
                closestEdgeArray[uParent] = edge;
            }
            if (weight < vParentWeight) {
                closestEdgeArray[vParent] = edge;
            }
        }
    }

    private double getvParentWeight(Integer firstValue, Integer secondValue) {
        return noOrientedWeightedGraph.getWeightFromEdge(firstValue,
                secondValue);
    }
}

