package domain;

import java.util.List;

public class Ladder {

    private final List<Line> lines;

    public Ladder(final List<Line> lines) {
        this.lines = lines;
    }

    public Position move(final Position position) {
        Position currentPosition = position;
        for (final Line line : lines) {
            final int movedX = line.move(currentPosition);
            currentPosition = new Position(movedX, lines.indexOf(line) + 1);
        }

        return currentPosition;
    }

    public List<Line> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "Ladder{" +
                "lines=" + lines +
                '}';
    }
}
