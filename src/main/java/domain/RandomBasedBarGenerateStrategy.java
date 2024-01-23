package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomBasedBarGenerateStrategy {

    public List<Boolean> generate(final int width) {
        final List<Boolean> bars = new ArrayList<>();
        boolean previous = false;
        for (int i = 0; i < width; i++) {
            final boolean bar = createBar(previous);
            bars.add(bar);
            previous = bar;
        }

        return Collections.unmodifiableList(bars);
    }

    private boolean createBar(final boolean hasPrevious) {
        if (hasPrevious) {
            return false;
        }
        return new Random().nextBoolean();
    }
}
