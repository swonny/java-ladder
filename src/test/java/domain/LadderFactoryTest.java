package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LadderFactoryTest {

    @Test
    void 지정한_높이의_사다리를_생성한다() {
        // given
        final int 사다리_높이 = 5;
        final int 참여자_수 = 3;

        // when
        final Ladder ladder = LadderFactory.of(new RandomBasedBarGenerateStrategy(), 사다리_높이, 참여자_수);

        // then
        System.out.println("ladder = " + ladder);
        assertThat(ladder.getLines()).hasSize(5);
    }
}
