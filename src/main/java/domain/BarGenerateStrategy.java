package domain;

import java.util.List;

public interface BarGenerateStrategy {

    List<Boolean> generate(final int barCount);
}
