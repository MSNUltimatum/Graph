package graphClasses;

import abstractClasses.Graph;
import abstractClasses.NonOrientedAbstractGraph;

import java.io.FileNotFoundException;
import java.util.Scanner;

class NoOrientedWeightedGraph<T> extends NonOrientedAbstractGraph<T> {
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
            Scanner sc = new Scanner(System.in);
            System.out.print("Would you like to change weight: Y(yes) ");
            String s = sc.nextLine();
            if ("Y".equals(s)) {
                setWeightToEdge(fromV, toV, weight);
            }
        } else {
            throw new RuntimeException("Bad data to insert");
        }
    }
}
