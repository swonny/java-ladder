package domain;

import java.util.List;

public class Ladder {

    private final List<Line> lines;

    public Ladder(final List<Line> lines) {
        this.lines = lines;
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
