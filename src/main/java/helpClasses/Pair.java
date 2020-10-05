package helpClasses;

import java.util.Objects;

public class Pair{
    private Integer firstValue;
    private Integer secondValue;
    private Double weight;

    public Integer getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Integer firstValue) {
        this.firstValue = firstValue;
    }

    public Integer getSecondValue() {
        return secondValue;
    }


    public void setSecondValue(Integer secondValue) {
        this.secondValue = secondValue;
    }

    public Pair(Integer firstValue, Integer secondValue, Double weight) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.weight = weight;
    }

    public Pair() {
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(firstValue, pair.firstValue) &&
                Objects.equals(secondValue, pair.secondValue) &&
                Objects.equals(weight, pair.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue, weight);
    }
}