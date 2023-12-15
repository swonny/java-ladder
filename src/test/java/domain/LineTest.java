package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

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
}
