package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LineTest {

    @Test
    void 연속적으로_true인_경우_예외를_던진다() {
        // given
        final List<Boolean> bars = List.of(false, true, true);

        // when & then
        assertThatThrownBy(() -> new Line(bars))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:1", "1:0", "2:3", "3:2"}, delimiter = ':')
    void 위치를_입력받은_경우_이동할_수_있는_위치로_이동한_위치를_반환한다_2(final int x, final int expected) {
        // given
        final Line line = new Line(List.of(true, false, true));
        final Position currentPosition = new Position(x, 0);

        // when
        final int movedX = line.move(currentPosition);

        // then
        assertThat(movedX).isEqualTo(expected);
    }
}
