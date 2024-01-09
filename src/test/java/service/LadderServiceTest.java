package service;

import domain.Ladder;
import domain.Line;
import domain.Participant;
import domain.Position;
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

        final Participant 승원 = new Participant("승원");
        final Participant 엔초 = new Participant("엔초");
        final Participant 제이미 = new Participant("제이미");
        final List<Participant> 참가자들 = List.of(승원, 엔초, 제이미);

        final String 승원_결과 = "승원_결과";
        final String 엔초_결과 = "엔초_결과";
        final String 제이미_결과 = "제이미_결과";
        final List<String> 게임_결과 = List.of(엔초_결과, 제이미_결과, 승원_결과);

        final LadderService ladderService = new LadderService(사다리, 참가자들, 게임_결과);

        // when
        ladderService.makeResult();

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(승원.getPosition())
                          .isEqualTo(new Position(2, 2));
            softAssertions.assertThat(엔초.getPosition())
                          .isEqualTo(new Position(0, 2));
            softAssertions.assertThat(제이미.getPosition())
                          .isEqualTo(new Position(1, 2));
        });
    }
}
