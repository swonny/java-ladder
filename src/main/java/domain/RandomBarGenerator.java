package domain;

import java.util.Random;

public class RandomBarGenerator implements BarGenerateStrategy {

    private static final Random random = new Random();

    @Override
    public boolean generate() {
        return random.nextBoolean();
    }
}
