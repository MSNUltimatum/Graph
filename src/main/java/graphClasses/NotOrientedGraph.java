package graphClasses;

import abstractClasses.NoOrientedAbstractGraph;
import interfaces.INotOriented;
import interfaces.INotWeighted;

import java.io.FileNotFoundException;
import java.util.List;

public class NotOrientedGraph<T> extends NoOrientedAbstractGraph<T> implements INotOriented<T>, INotWeighted<T> {
    public NotOrientedGraph() {
        super();
    }

    public NotOrientedGraph(NotOrientedGraph<T> g) {
        super(g);
    }

    public NotOrientedGraph(String fileName, Class<T> tClass) throws FileNotFoundException {
        super(fileName, tClass);
    }

    @Override
    public void addEdge(T fromV, T toV) {
        if(hasVertex(fromV) && hasVertex(toV) && !hasEdge(fromV, toV)){
            graph.get(fromV).put(toV, null);
            graph.get(toV).put(fromV, null);
        } else {
            throw new RuntimeException("Bad data to insert");
        }
    }

}
