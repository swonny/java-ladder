package domain;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LineFactoryTest {

    @RepeatedTest(10)
    void true가_연속으로_두_번_생성되지_않는다() {
        // given
        final BarGenerateStrategy barGenerator = new RandomBarGenerator();
        final int barCount = 3;

        // when
        final Line line = LineFactory.from(barGenerator, barCount);
        final List<Boolean> bars = line.getBars();

        // then
        assertThat(bars.get(0) && bars.get(1)).isFalse();
    }
}
