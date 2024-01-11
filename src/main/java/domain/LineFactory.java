package domain;

import java.util.List;

public class LineFactory {

    private static final int SUBTRACT_FOR_WIDTH_COUNT = 1;

    public static Line from(final BarGenerateStrategy generateStrategy, final int participantCount) {
        final int barCount = participantCount - SUBTRACT_FOR_WIDTH_COUNT;
        final List<Boolean> bars = generateStrategy.generate(barCount);

        return new Line(bars);
    }
}
