package domain;

import java.util.List;

public class Line {

    private final List<Boolean> bars;

    public Line(final List<Boolean> bars) {
        validate(bars);
        this.bars = bars;
    }

    private void validate(final List<Boolean> bars) {
        boolean previous = false;
        for (final boolean bar : bars) {
            validateContinuousTrue(previous, bar);
            previous = bar;
        }
    }

    private void validateContinuousTrue(final boolean previous, final boolean current) {
        if (previous && current) {
            throw new IllegalArgumentException("사다리는 연속적으로 존재할 수 없습니다.");
        }
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
