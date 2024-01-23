package fixture;

import domain.BarGenerateStrategy;

import java.util.ArrayList;
import java.util.List;

public class TestRandomBarGenerator implements BarGenerateStrategy {

    private final List<Boolean> bars;

    public TestRandomBarGenerator(final List<Boolean> bars) {
        this.bars = new ArrayList<>(bars);
    }

    @Override
    public boolean generate() {
        return bars.remove(0);
    }
}
