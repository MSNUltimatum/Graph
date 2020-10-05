package graphClasses;

import abstractClasses.Graph;

import java.util.*;

public class BoruvkaAlgorithm<T> {
    private final Graph<Integer> noOrientedWeightedGraph;
    private final Graph<Integer> mst;
    private final UnionFind uf;
    private int totalWeight = 0;
    private final List<Pair> edges;
    private int mstEdgesSize = 0;
    private Pair[] closestEdgeArray;


    public BoruvkaAlgorithm(Graph<Integer> graph) {
        this.noOrientedWeightedGraph = graph;
        uf = new UnionFind(graph.getVertexesCount());
        mst = new NoOrientedWeightedGraph<>();
        noOrientedWeightedGraph.getGraph().keySet().forEach(mst::addVertex);
        edges = new ArrayList<>();
        fillAgesArray();
    }

    private void fillAgesArray() {
        noOrientedWeightedGraph.getGraph().forEach((key, val) -> {
            val.forEach((key1, val1) -> {
                Pair edge = new Pair();
                makePair(key, key1, edge);
                edge.setWeight(val1);
                if(!edges.contains(edge)){
                    edges.add(edge);
                }
            });
        });
    }

    private void makePair(Integer key, Integer key1, Pair edge) {
        if(key >= key1){
            edge.setFirstValue(key1);
            edge.setSecondValue(key);
        } else {
            edge.setFirstValue(key);
            edge.setSecondValue(key1);
        }
    }

    public Graph<Integer> Boruvka_Mst() {
        int size = noOrientedWeightedGraph.getVertexesCount();
        for (int t = 1; t < size && mstEdgesSize < size - 1; t = t + t) {
            closestEdgeArray = new Pair[size];
            fillClosestArray();
            treeMaker(size);
        }
        mst.printGraphToConsole();
        System.out.println(totalWeight);
        return mst;
    }

    private void treeMaker(int size) {
        for (int i = 0; i < size; i++) {
            Pair edge = closestEdgeArray[i];
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
        for (Pair edge : edges) {
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

class Pair{
    private Integer firstValue;
    private Integer secondValue;
    private Double weight;

    public Integer getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Integer firstValue) {
        this.firstValue = firstValue;
    }

    public Integer getSecondValue() {
        return secondValue;
    }


    public void setSecondValue(Integer secondValue) {
        this.secondValue = secondValue;
    }

    public Pair(Integer firstValue, Integer secondValue, Double weight) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.weight = weight;
    }

    public Pair() {
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(firstValue, pair.firstValue) &&
                Objects.equals(secondValue, pair.secondValue) &&
                Objects.equals(weight, pair.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue, weight);
    }
}

class UnionFind<T> {
    private final Map<Integer, Integer> parents;
    private final Map<Integer, Integer> ranks;

    public UnionFind(int n) {
        parents = new HashMap<>();
        ranks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            parents.put(i, i);
            ranks.put(i, 0);
        }
    }

    public Integer find(Integer u) {
        while (!u.equals(parents.get(u))) {
            u = parents.get(u);
        }
        return u;
    }

    public void union(Integer u, Integer v) {
        Integer uParent = find(u);
        Integer vParent = find(v);
        if (uParent.equals(vParent)) {
            return;
        }

        if (ranks.get(uParent) < ranks.get(vParent)) {
            parents.put(uParent, vParent);
        } else if (ranks.get(uParent) > ranks.get(vParent)) {
            parents.put(vParent, uParent);
        } else {
            parents.put(vParent, uParent);
            ranks.put(uParent, ranks.get(uParent) + 1);
        }
    }
}

