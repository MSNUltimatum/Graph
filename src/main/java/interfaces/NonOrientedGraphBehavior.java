package interfaces;

public interface NonOrientedGraphBehavior <T> extends IGraph<T> {
    void findDistances(T u);
}
