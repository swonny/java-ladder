package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantFactoryTest {

    @Test
    void 사용자_생성시_일급컬렉션에_저장된다() {
        // given
        final String 이름 = "승원";

        // when
        final Participant 승원 = ParticipantFactory.from(이름);

        // then
        assertThat(Participants.findParticipant("승원")).isEqualTo(승원);
    }
}
