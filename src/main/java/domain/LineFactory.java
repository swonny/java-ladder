package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineFactory {

    private static final int SUBTRACT_FOR_WIDTH_COUNT = 1;

    public static Line from(final BarGenerateStrategy generateStrategy, final int participantCount) {
        final int barCount = participantCount - SUBTRACT_FOR_WIDTH_COUNT;
        final List<Boolean> bars = generateBars(generateStrategy, barCount);

        return new Line(bars);
    }

    private static List<Boolean> generateBars(final BarGenerateStrategy generateStrategy, final int barCount) {
        final List<Boolean> bars = new ArrayList<>();
        boolean previous = false;
        for (int i = 0; i < barCount; i++) {
            final boolean bar = createBar(generateStrategy, previous);
            bars.add(bar);
            previous = bar;
        }

        return Collections.unmodifiableList(bars);
    }

    private static boolean createBar(final BarGenerateStrategy generateStrategy, boolean hasPrevious) {
        if (hasPrevious) {
            return false;
        }
        return generateStrategy.generate();
    }
}
