package domain;

import java.util.List;
import java.util.Objects;

public class Position {

    private final int x;
    private final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Position add(final int x, final int y) {
        return new Position(this.x + x, this.y + y);
    }

    public boolean isBiggerThan(final int x, final int y) {
        return this.x > x || this.y > y;
    }

    public Line getCurrentLine(final List<Line> lines) {
        return lines.get(y);
    }

    public int getCurrentX() {
        return x;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
