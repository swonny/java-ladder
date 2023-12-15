package domain;

import java.util.ArrayList;
import java.util.List;

public class LadderFactory {

    public static Ladder of(final BarGenerateStrategy generateStrategy, final int height, final int participantCount) {
        final List<Line> lines = new ArrayList<>();
        for (int currentHeight = 0; currentHeight < height; currentHeight++) {
            final Line newLine = LineFactory.from(generateStrategy, participantCount);
            lines.add(newLine);
        }

        return new Ladder(lines);
    }
}
