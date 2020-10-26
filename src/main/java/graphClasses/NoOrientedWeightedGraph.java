package graphClasses;

import abstractClasses.NoOrientedAbstractGraph;
import interfaces.INotOriented;
import interfaces.IWeighted;

import java.io.FileNotFoundException;
import java.util.*;

public class NoOrientedWeightedGraph<T> extends NoOrientedAbstractGraph<T> implements IWeighted<T>, INotOriented<T> {
    public NoOrientedWeightedGraph() {
        super();
    }

    public NoOrientedWeightedGraph(NoOrientedWeightedGraph<T> g) {
        super(g);
    }

    public NoOrientedWeightedGraph(String fileName, Class<T> tClass) throws FileNotFoundException {
        super(fileName, tClass);
    }

    @Override
    public void addEdge(T fromV, T toV, double weight) {
        if(hasVertex(fromV) && hasVertex(toV) && !hasEdge(fromV, toV)){
            graph.get(fromV).put(toV, weight);
            graph.get(toV).put(fromV, weight);
        } else if(hasEdge(fromV, toV) && getWeightFromEdge(fromV, toV) != weight){
            changeWeight(fromV, toV, weight);
        } else {
            throw new RuntimeException("Bad data to insert");
        }
    }

    @Override
    public double getWeightFromEdge(T fromV, T toV) {
        if(hasEdge(fromV, toV)) {
            return graph.get(fromV).get(toV);
        } else {
            throw new IllegalStateException("Bad data to get");
        }
    }

    @Override
    public void setWeightToEdge(T fromV, T toV, double weight) {
        if(hasEdge(fromV, toV)) {
            graph.get(fromV).put(toV, weight);
        } else {
            throw new IllegalStateException("Bad data to change");
        }
    }

}
