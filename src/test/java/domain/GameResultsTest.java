package domain;

import domain.fixture.GameResultsFixture;
import domain.fixture.ParticipantsFixture;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static domain.fixture.GameResultsFixture.getGameResult;
import static domain.fixture.ParticipantsFixture.getParticipant;

class GameResultsTest {

    @Test
    void 참여자에_해당하는_게임_결과를_알_수_있다() {
        // given
        final Participants 게임_참여자들 = ParticipantsFixture.of("승원", "메리");
        final GameResults 게임결과 = GameResultsFixture.of("당첨", "꽝");

        ParticipantsFixture.move("승원", new Position(1, 1));
        ParticipantsFixture.move("메리", new Position(0, 1));

        // when
        final Map<Participant, GameResult> 참여자_게임_결과 = 게임결과.findMatchingResults(게임_참여자들);

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(참여자_게임_결과.get(getParticipant("승원")))
                          .isEqualTo(getGameResult("꽝"));
            softAssertions.assertThat(참여자_게임_결과.get(getParticipant("메리")))
                          .isEqualTo(getGameResult("당첨"));
        });
    }
}
