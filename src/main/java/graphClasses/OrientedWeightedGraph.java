package graphClasses;

import abstractClasses.OrientedAbstractClass;
import interfaces.INotWeighted;
import interfaces.IOriented;
import interfaces.IWeighted;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class OrientedWeightedGraph<T> extends OrientedAbstractClass<T> implements IOriented<T>, IWeighted<T> {
    public OrientedWeightedGraph() {
        super();
    }

    public OrientedWeightedGraph(OrientedWeightedGraph<T> g) {
        super(g);
    }

    public OrientedWeightedGraph(String fileName, Class<T> tClass) throws FileNotFoundException {
        super(fileName, tClass);
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

    @Override
    public void addEdge(T fromV, T toV, double weight) {
        if(hasVertex(fromV) && hasVertex(toV) && !hasEdge(fromV, toV)){
            graph.get(fromV).put(toV, weight);
        } else if(hasEdge(fromV, toV) && getWeightFromEdge(fromV, toV) != weight){
            changeWeight(fromV, toV, weight);
        } else {
            throw new RuntimeException("Bad data to insert");
        }
    }

    @Override
    protected void fillWeightedGraph(List<String[]> lines, Class<T> tClass) {
        lines.forEach(line ->{
            for (int i = 1; i < line.length; i++) {
                addEdge(castArg(tClass, line[0]), castArg(tClass, line[i].split("=")[0]), Double.parseDouble(line[i].split("=")[1]));
            }
        });
    }
}
