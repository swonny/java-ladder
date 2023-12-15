package domain;

import java.util.Random;

public class RandomBasedBarGenerateStrategy implements BarGenerateStrategy {

    @Override
    public boolean generate() {
        return new Random().nextBoolean();
    }
}
