package domain;

import java.util.List;

public class Line {

    private final List<Boolean> bars;

    public Line(final List<Boolean> bars) {
        this.bars = bars;
    }

    public List<Boolean> getBars() {
        return bars;
    }

    @Override
    public String toString() {
        return "Line{" +
                "bars=" + bars +
                '}';
    }
}
