package controller.dto;

import domain.Ladder;
import domain.Line;

import java.util.List;
import java.util.stream.Collectors;

public class LadderDto {

    private final List<List<Boolean>> ladder;

    private LadderDto(final List<List<Boolean>> ladder) {
        this.ladder = ladder;
    }

    public static LadderDto from(final Ladder ladder) {
        final List<List<Boolean>> rawLadder = ladder.getLines()
                                                    .stream()
                                                    .map(Line::getBars)
                                                    .collect(Collectors.toUnmodifiableList());

        return new LadderDto(rawLadder);
    }

    public List<List<Boolean>> getLadder() {
        return ladder;
    }

    static class LineDto {

        private final List<Boolean> bars;

        LineDto(final List<Boolean> bars) {
            this.bars = bars;
        }

        public List<Boolean> getBars() {
            return bars;
        }
    }
}
