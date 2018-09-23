package morabem.utils;

public class PrecoData {
    public Double min, max;

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "$classname{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
