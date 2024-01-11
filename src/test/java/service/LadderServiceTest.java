package service;

import domain.GameResults;
import domain.Ladder;
import domain.Line;
import domain.Participants;
import domain.Position;
import fixture.GameResultsFixture;
import fixture.ParticipantsFixture;
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
        final Line 첫번째_줄 = new Line(List.of(true, false));
        final Line 두번째_줄 = new Line(List.of(false, true));
        final Ladder 사다리 = new Ladder(List.of(첫번째_줄, 두번째_줄));

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
