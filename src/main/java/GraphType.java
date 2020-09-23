import abstractClasses.Graph;
import graphClasses.NoOrientedWeightedGraph;
import graphClasses.NotOrientedGraph;
import graphClasses.OrientedGraph;

public enum GraphType {
    NOTORIENTED,
    NOTORIENTEDWEIGHTED,
    ORIENTED,
    ORIENTEDWEIGHTED;

    public static GraphType getGraphType(Graph graph){
        if(graph instanceof NotOrientedGraph)
            return NOTORIENTED;
        if(graph instanceof NoOrientedWeightedGraph)
            return NOTORIENTEDWEIGHTED;
        if(graph instanceof OrientedGraph)
            return ORIENTED;

        return ORIENTEDWEIGHTED;
    }
}
