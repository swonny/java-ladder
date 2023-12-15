package domain;

import java.util.ArrayList;
import java.util.List;

public class LadderFactory {

    public static Ladder of(final BarGenerateStrategy generateStrategy, final int height, final int participants) {
        final List<Line> lines = new ArrayList<>();
        for (int currentHeight = 0; currentHeight < height; currentHeight++) {
            final Line newLine = createLine(generateStrategy, participants);
            lines.add(newLine);
        }

        return new Ladder(lines);
    }

    private static Line createLine(final BarGenerateStrategy generateStrategy, final int participants) {
        final List<Boolean> bars = new ArrayList<>();
        for (int currentParticipant = 0; currentParticipant < participants; currentParticipant++) {
            bars.add(generateStrategy.generate());
        }

        return new Line(bars);
    }
}
