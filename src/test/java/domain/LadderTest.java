package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {

    /**
     * pobi  honux crong   jk
     * |-----|     |-----|
     * |     |-----|     |
     * |-----|     |     |
     * |     |-----|     |
     * |-----|     |-----|
     * 꽝   5000  꽝    3000
     */
    @ParameterizedTest
    @CsvSource(value = {"0:0:0:5", "1:0:3:5", "2:0:2:5", "3:0:1:5"}, delimiter = ':')
    void 특정_위치_참가자의_게임_실행_결과를_계산한다(final int startX, final int startY, final int expectedX, final int expectedY) {
        // given
        final Line firstLine = new Line(List.of(true, false, true));
        final Line secondLine = new Line(List.of(false, true, false));
        final Line thirdLine = new Line(List.of(true, false, false));
        final Line fourthLine = new Line(List.of(false, true, false));
        final Line fifthLine = new Line(List.of(true, false, true));
        final Ladder ladder = new Ladder(List.of(firstLine, secondLine, thirdLine, fourthLine, fifthLine));
        final Position startPosition = new Position(startX, startY);

        // when
        final Position actual = ladder.move(startPosition);

        // then
        assertThat(actual).isEqualTo(new Position(expectedX, expectedY));
    }
}
