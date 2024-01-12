package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameTest {

    @Test
    void 이름이_1글자_이상_5글자_이하인_경우_정상_동작한다() {
        // given
        final String validName = "유효한이름";

        // when & then
        assertDoesNotThrow(() -> new Name(validName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "다섯글자초과"})
    void 참여자의_이름이_1글자_미만_5글자를_초과하면_예외가_발생한다(final String invalidLengthName) {
        // when & then
        assertThatThrownBy(() -> new Name(invalidLengthName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
