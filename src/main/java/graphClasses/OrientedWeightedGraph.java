package graphClasses;

import abstractClasses.Graph;
import abstractClasses.OrientedAbstractClass;

import java.io.FileNotFoundException;
import java.util.Scanner;

class OrientedWeightedGraph<T> extends OrientedAbstractClass<T> {
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
    public void addEdge(T fromV, T toV, double weight) {
        if(hasVertex(fromV) && hasVertex(toV) && !hasEdge(fromV, toV)){
            graph.get(fromV).put(toV, weight);
        } else if(hasEdge(fromV, toV) && getWeightFromEdge(fromV, toV) != weight){
            Scanner sc = new Scanner(System.in);
            System.out.print("Would you like to change weight: Y(yes) ");
            String s = sc.nextLine();
            if ("Y".equals(s)) {
                setWeightToEdge(fromV, toV, weight);
            }
        }else {
            throw new RuntimeException("Bad data to insert");
        }
    }

}
