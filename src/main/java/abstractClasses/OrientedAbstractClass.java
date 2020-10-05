package abstractClasses;

import graphClasses.GraphFactory;
import graphClasses.GraphType;
import interfaces.OrientedGraphBehavior;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class OrientedAbstractClass<T> extends Graph<T> implements OrientedGraphBehavior<T> {
    public OrientedAbstractClass(){
        super();
    }

    public OrientedAbstractClass(Graph<T> g){
        super(g);
    }

    public OrientedAbstractClass(String fileName, Class<T> tClass) throws FileNotFoundException {
        super(fileName, tClass);
    }

    @Override
    public Set<T> findCommonIncomingVertex(T v, T u) {
        if(hasVertex(v) && hasVertex(u)) {
            Set<T> tsV = graph.get(v).keySet();
            Set<T> tsU = graph.get(u).keySet();
            Set<T> collect = tsU.stream().filter(tsV::contains).collect(Collectors.toSet());
            if (collect.size() > 0) {
                collect.forEach(e -> System.out.printf("%s ", e));
                System.out.println();
                return collect;
            } else {
                System.out.println("No this vertexes");
                throw new RuntimeException("No common incoming vertexes");
            }
        } else {
            throw new RuntimeException("Bad input data");
        }
    }

    @Override
    public Set<T> findAllIncomingAndOutgoingVertex(T u) {
        if(hasVertex(u)) {
            Set<T> tsU = graph.get(u).keySet();
            Set<T> collect = tsU.stream().filter(e -> graph.get(e).containsKey(u)).collect(Collectors.toSet());
            if (collect.size() > 0) {
                collect.forEach(e -> System.out.printf("%s ", e));
                System.out.println();
                return collect;
            } else
                System.out.println("No this vertexes");
            throw new RuntimeException("No inc. and out. vertexes");
        } else {
            throw new RuntimeException("Bad input data");
        }
    }

    @Override
    public void deleteEdge(T from, T to) {
        if(hasVertex(from) && hasVertex(to) && hasEdge(from, to)){
            graph.get(from).remove(to);
        }
    }

    @Override
    public boolean CheckTree() {
        Set<T> vertexesForDelete = findVertexes();
        for (T val: vertexesForDelete) {
            if (checkCurrentDeleteVertex(val))
                return true;
        }
        return false;
    }

    private boolean checkCurrentDeleteVertex(T val) {
        HashMap<T, HashMap<T, Double>> graph1 = new GraphFactory<T>()
                .makeGraph(GraphType.getGraphType(this), this)
                .getGraph();
        deleteVertexWithEdges(val);
        if (checkGraphWithotVertex())
            return true;
        this.graph = graph1;
        return false;
    }

    private boolean checkGraphWithotVertex() {
        Set<T> valsDFS = new HashSet<>();
        T s = graph.keySet().stream().findFirst().get();
        DFS(s, valsDFS);
        if(valsDFS.equals(graph.keySet())){
            return checkTreePow();
        }
        return false;
    }

    private Set<T> findVertexes() {
        int vertexCount = getVertexesCount();
        int edgesCount = getEdgesCountInOrientedGraph();
        return graph.keySet().stream()
                .filter(i -> vertexCount - 1 == edgesCount - getPow(i) + 1).collect(Collectors.toSet());
    }

    private boolean checkTreePow(){
        List<T> tList = graph.keySet().stream()
                .flatMap(i -> graph.get(i).keySet().stream())
                .collect(Collectors.toList());
        Set<T> allItems = new HashSet<>();
        return tList.stream().allMatch(allItems::add);
    }

    @Override
    public int getEdgesCountInOrientedGraph(){
        return graph.keySet().stream().mapToInt(e -> graph.get(e).size()).sum();
    }

    @Override
    public void deleteVertexWithEdges(T v) {
        if(hasVertex(v)){
            Set<T> fromThisVertex = graph.keySet().stream().filter(i -> graph.get(i).containsKey(v)).collect(Collectors.toSet());
            Set<T> toThisVertex = new HashSet<>(graph.get(v).keySet());
            fromThisVertex.forEach(e -> deleteEdge(e, v));
            toThisVertex.forEach(e -> deleteEdge(v, e));
            graph.remove(v);
        }
    }

    private int getPow(T e){
        return (int) (graph.keySet().stream().filter(i -> graph.get(i).containsKey(e)).count() + graph.get(e).size());
    }
}
