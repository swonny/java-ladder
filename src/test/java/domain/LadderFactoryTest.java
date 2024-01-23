package domain;

import fixture.TestRandomBarGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LadderFactoryTest {

    /**
     * a   b   c
     * |---|   |
     * |   |---|
     * |   |   |
     * |---|   |
     * |   |---|
     */
    @Test
    void 지정한_높이의_사다리를_생성한다() {
        // given
        final int 사다리_높이 = 5;
        final int 참여자_수 = 3;
        final BarGenerateStrategy barGenerator = new TestRandomBarGenerator(
                List.of(true, false, false, true, false, false, true, false, false, true)
        );

        // when
        final Ladder ladder = LadderFactory.of(barGenerator, 사다리_높이, 참여자_수);

        // then
        assertThat(ladder.getLines()).hasSize(5);
    }
}
