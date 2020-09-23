package abstractClasses;

import interfaces.OrientedGraphBehavior;

import java.io.FileNotFoundException;
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


}
