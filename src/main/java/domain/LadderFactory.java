package domain;

import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableList;

public class LadderFactory {

    public static Ladder of(final BarGenerateStrategy generateStrategy, final int height, final int participantCount) {
        return Stream.generate(() -> LineFactory.from(generateStrategy, participantCount))
                     .limit(height)
                     .collect(collectingAndThen(toUnmodifiableList(), Ladder::new));
    }
}
