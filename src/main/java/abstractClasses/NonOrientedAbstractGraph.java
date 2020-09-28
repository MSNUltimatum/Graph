package abstractClasses;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public abstract class NonOrientedAbstractGraph<T> extends Graph<T>{
    public NonOrientedAbstractGraph(){
        super();
    }
    public NonOrientedAbstractGraph(Graph<T> g){
        super(g);
    }
    public NonOrientedAbstractGraph(String fileName, Class<T> tClass) throws FileNotFoundException {
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
}
