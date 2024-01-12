package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @Test
    void 참여자_위치를_이동할_수_있다() {
        // given
        final Participant 참여자 = new Participant(new Name("참여자"));
        final Position 참여자_위치 = new Position(0, 0);

        // when
        참여자.move(참여자_위치);

        // then
        assertThat(참여자.getPosition())
                .isEqualTo(new Position(0, 0));
    }
}
