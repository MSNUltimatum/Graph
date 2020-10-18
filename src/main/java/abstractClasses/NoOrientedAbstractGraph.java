package abstractClasses;

import graphClasses.GraphType;
import graphClasses.DijkstraAlgorithm;
import interfaces.INotOriented;

import java.io.FileNotFoundException;
import java.util.*;

public abstract class NoOrientedAbstractGraph<T> extends Graph<T> implements INotOriented<T> {
    DijkstraAlgorithm<T> dijkstraAlgorithmTasks = new DijkstraAlgorithm<>(this);

    public NoOrientedAbstractGraph(){
        super();
    }
    public NoOrientedAbstractGraph(Graph<T> g){
        super(g);
    }
    public NoOrientedAbstractGraph(String fileName, Class<T> tClass) throws FileNotFoundException {
        super(fileName, tClass);
    }

    @Override
    public void deleteEdge(T from, T to) {
        if(hasVertex(from) && hasVertex(to) && hasEdge(from, to)){
            graph.get(from).remove(to);
            graph.get(to).remove(from);
        }
    }

    @Override
    public void deleteVertexWithEdges(T v) {
        if(hasVertex(v)){
            Set<T> ts = graph.get(v).keySet();
            Set<T> copySet = new HashSet<>(ts);
            copySet.forEach(e -> deleteEdge(v, e));
            graph.remove(v);
        }
    }

    @Override
    public Map<T, Integer> countOfMinimumWays(T u) {
        GraphType graphType = GraphType.getGraphType(this);
        if(graphType == GraphType.NOTORIENTED || graphType == GraphType.NOTORIENTEDWEIGHTED) {
            return dijkstraAlgorithmTasks.findCountOfMinWaysToAllVertexes(u);
        } else {
            throw new IllegalStateException("Graph is oriented");
        }
    }

    @Override
    public Map<T, Double> findDistances(T u) {
        GraphType graphType = GraphType.getGraphType(this);
        if(graphType == GraphType.NOTORIENTED || graphType == GraphType.NOTORIENTEDWEIGHTED) {
            return dijkstraAlgorithmTasks.findMinimumDistances(u);
        } else {
            throw new IllegalStateException("Graph is oriented");
        }
    }
}
