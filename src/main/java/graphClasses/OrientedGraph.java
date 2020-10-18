package graphClasses;

import abstractClasses.OrientedAbstractClass;
import interfaces.INotWeighted;
import interfaces.IOriented;

import java.io.FileNotFoundException;
import java.util.List;

public class OrientedGraph<T> extends OrientedAbstractClass<T> implements IOriented<T>, INotWeighted<T> {
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

    @Override
    protected void fillNoWeightedGraph(List<String[]> lines, Class<T> tClass) {
        lines.forEach(line ->{
            for (int i = 1; i < line.length; i++) {
                addEdge(castArg(tClass, line[0]), castArg(tClass, line[i]));
            }
        });
    }
}
