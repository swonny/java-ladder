package domain;

import java.util.List;

public class Line {

    private static final int START_POINT = 0;

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

    public int move(final Position position) {
        final int currentX = position.getX();
        if (isLeftMovable(currentX)) {
            return calculateLeft(currentX);
        }
        if (isRightMovable(currentX)) {
            return calculateRight(currentX);
        }
        return currentX;
    }

    private boolean isLeftMovable(final int current) {
        if (canMoveLeft(current)) {
            final int left = calculateLeft(current);
            return isConnected(left, current);
        }
        return false;
    }

    private boolean canMoveLeft(final int current) {
        return current > START_POINT;
    }

    private int calculateLeft(final int currentX) {
        if (currentX == START_POINT) {
            return currentX;
        }
        return currentX - 1;
    }

    private boolean isConnected(final int currentX, final int movablePosition) {
        if (currentX == movablePosition) {
            return false;
        }
        if (currentX < movablePosition) {
            return bars.get(currentX);
        }
        return bars.get(currentX - 1);
    }

    private boolean isRightMovable(final int current) {
        if (canMoveRight(current)) {
            final int right = calculateRight(current);
            return isConnected(right, current);
        }
        return false;
    }

    private boolean canMoveRight(final int current) {
        return current < bars.size();
    }

    private int calculateRight(final int currentX) {
        if (currentX > bars.size() - 1) {
            return currentX;
        }
        return currentX + 1;
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
