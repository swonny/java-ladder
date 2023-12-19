package domain;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomBasedBarGenerateStrategyTest {

    @RepeatedTest(10)
    void true가_연속으로_두_번_생성되지_않는다() {
        // given
        final RandomBasedBarGenerateStrategy randomBasedBarGenerateStrategy = new RandomBasedBarGenerateStrategy();
        final int width = 2;

        // when
        final List<Boolean> bars = randomBasedBarGenerateStrategy.generate(width);

        // then
        assertThat(bars.get(0) && bars.get(1)).isFalse();
    }
}
