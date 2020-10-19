package helpClasses;

public class FlowEdge{
    private Integer target;
    private Integer reversed;
    private Double capacity;
    private Double flow;

    public FlowEdge(Integer target, Integer reversed, Double capacity) {
        this.target = target;
        this.reversed = reversed;
        this.capacity = capacity;
        this.flow = 0d;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getReversed() {
        return reversed;
    }

    public void setReversed(Integer reversed) {
        this.reversed = reversed;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }
}
