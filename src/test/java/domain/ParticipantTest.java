package domain;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantTest {

    @Nested
    class 참여자를_생성시 {

        @Test
        void 참여자의_이름이_1글자_이상_5글자_이하인_경우_정상_동작한다() {
            // given
            final String validName = "유효한이름";

            // when & then
            assertDoesNotThrow(() -> new Participant(validName));
        }

        @Test
        void 참여자의_이름이_5글자를_초과하면_예외가_발생한다() {
            // given
            final String invalidLengthName = "5글자를 초과하는 이름";

            // when & then
            assertThatThrownBy(() -> new Participant(invalidLengthName))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
