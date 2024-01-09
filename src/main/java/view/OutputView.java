package view;

import controller.dto.LadderDto;
import domain.Participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.Participant.MINIMUM_NAME_LENGTH;

public class OutputView {

    private static final String GAME_RESULT_PREFIX = "실행 결과";
    private static final String LADDER_BAR_DELIMITER = "|";
    private static final String GAME_RESULT_DELIMITER = ":";

    public void printLadder(
            final LadderDto ladderDto,
            final List<Participant> participants,
            final List<String> gameResults
    ) {
        final int maximumNameSize = calculateMaximumNameSize(participants);
        printParticipants(participants, maximumNameSize);
        printLines(ladderDto, maximumNameSize);
        printGameResults(gameResults);
    }

    private void printGameResults(final List<String> gameResults) {
        final String joinedResults = String.join(" ", gameResults);
        System.out.println(joinedResults);
    }

    private int calculateMaximumNameSize(final List<Participant> participants) {
        return participants.stream()
                           .map(Participant::getName)
                           .mapToInt(String::length)
                           .max()
                           .orElse(MINIMUM_NAME_LENGTH);
    }

    private void printParticipants(final List<Participant> participants, final int maximumNameLength) {
        final String participantsView = participants.stream()
                                                    .map(Participant::getName)
                                                    .collect(Collectors.joining(LadderSymbol.SPACE.symbol));
        System.out.println(participantsView);
    }

    private void printLines(final LadderDto ladderDto, final int maximumNameSize) {
        final List<List<Boolean>> ladder = ladderDto.getLadder();
        for (final List<Boolean> line : ladder) {
            final String lineView = line.stream()
                                        .map(rawLine -> LadderSymbol.findSymbol(rawLine)
                                                                    .repeat(maximumNameSize)
                                        ).collect(Collectors.joining(LADDER_BAR_DELIMITER));
            System.out.println(LADDER_BAR_DELIMITER + lineView + LADDER_BAR_DELIMITER);
        }
    }

    public void printResult(final String gameResult) {
        System.out.println(GAME_RESULT_PREFIX);
        System.out.println(gameResult);
    }

    public void printAll(final Map<Participant, String> gameResults) {
        System.out.println(GAME_RESULT_PREFIX);
        gameResults.keySet()
                   .stream()
                   .map(participant -> String.join(GAME_RESULT_DELIMITER, participant.getName(), gameResults.get(participant)))
                   .forEach(System.out::println);
    }

    enum LadderSymbol {

        SPACE(" ", false),
        BAR("-", true);

        private final String symbol;
        private final boolean isExist;

        LadderSymbol(final String symbol, final boolean isExist) {
            this.symbol = symbol;
            this.isExist = isExist;
        }

        public static String findSymbol(final boolean isExist) {
            if (isExist) {
                return BAR.symbol;
            }
            return SPACE.symbol;
        }
    }
}
