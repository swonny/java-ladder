package domain;

import java.util.List;

public class LineFactory {

    public static Line from(final BarGenerateStrategy generateStrategy, final int participantCount) {
        final List<Boolean> bars = generateStrategy.generate(participantCount);

        return new Line(bars);
    }
}
