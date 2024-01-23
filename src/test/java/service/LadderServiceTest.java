package service;

import domain.BarGenerateStrategy;
import domain.GameResults;
import domain.Ladder;
import domain.LadderFactory;
import domain.Participants;
import domain.Position;
import fixture.GameResultsFixture;
import fixture.ParticipantsFixture;
import fixture.TestRandomBarGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class LadderServiceTest {

    /**
     * 승원   엔초   제이미
     * |-----|     |
     * |     |-----|
     * 엔초  제이미  승원
     */
    @Test
    void 게임을_실행하면_결과를_저장한다() {
        // given
        final BarGenerateStrategy barGenerator = new TestRandomBarGenerator(List.of(true, false, true)); // true, false, false, true
        final Ladder 사다리 = LadderFactory.of(barGenerator, 2, 3);
        final Participants 참가자들 = ParticipantsFixture.of("승원", "엔초", "제이미");
        final GameResults 게임_결과 = GameResultsFixture.of("승원_결과", "엔초_결과", "제이미_결과");

        final LadderService ladderService = new LadderService(사다리, 참가자들, 게임_결과);

        // when
        ladderService.calculateResult();

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(ParticipantsFixture.getParticipant("승원").getPosition())
                          .isEqualTo(new Position(2, 2));
            softAssertions.assertThat(ParticipantsFixture.getParticipant("엔초").getPosition())
                          .isEqualTo(new Position(0, 2));
            softAssertions.assertThat(ParticipantsFixture.getParticipant("제이미").getPosition())
                          .isEqualTo(new Position(1, 2));
        });
    }
}
