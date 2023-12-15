package domain;

import java.util.List;

public class Line {

    private final List<Boolean> bars;

    public Line(final List<Boolean> bars) {
        this.bars = bars;
    }

    @Override
    public String toString() {
        return "Line{" +
                "bars=" + bars +
                '}';
    }
}
