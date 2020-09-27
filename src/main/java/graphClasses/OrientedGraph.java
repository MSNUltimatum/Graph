package graphClasses;

import abstractClasses.Graph;
import abstractClasses.OrientedAbstractClass;

import java.io.FileNotFoundException;

class OrientedGraph<T> extends OrientedAbstractClass<T> {
    public OrientedGraph() {
        super();
    }

    public OrientedGraph(OrientedGraph<T> g) {
        super(g);
    }

    public OrientedGraph(String fileName, Class<T> tClass) throws FileNotFoundException {
        super(fileName, tClass);
    }

    @Override
    public void addEdge(T fromV, T toV) {
        if(hasVertex(fromV) && hasVertex(toV) && !hasEdge(fromV, toV)){
            graph.get(fromV).put(toV, null);
        } else if(!hasVertex(fromV) || !hasVertex(toV)) {
            throw new RuntimeException("Bad vertexes");
        } else if(hasEdge(fromV, toV)) {
            throw new RuntimeException("Existed edge (" + fromV + ", " + toV + ")");
        }
    }
}
