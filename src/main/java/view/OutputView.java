package view;

import controller.dto.LadderDto;
import controller.dto.ParticipantResultsDto;
import domain.GameResult;
import domain.Participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.Participant.MINIMUM_NAME_LENGTH;

public class OutputView {

    private static final String GAME_RESULT_PREFIX = "실행 결과";
    private static final String LADDER_BAR_DELIMITER = "|";
    private static final String GAME_RESULT_DELIMITER = ":";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printLadder(
            final LadderDto ladderDto,
            final List<Participant> participants,
            final List<GameResult> gameResults
    ) {
        final int maximumNameSize = calculateMaximumNameSize(participants);
        printParticipants(participants, maximumNameSize);
        printLines(ladderDto, maximumNameSize);
        printGameResults(gameResults);
    }

    private void printGameResults(final List<GameResult> gameResults) {
        final List<String> gameResultValues = gameResults.stream()
                                                         .map(GameResult::getValue)
                                                         .collect(Collectors.toUnmodifiableList());
        final String joinedResults = String.join(" ", gameResultValues);
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

    public void printResult(final ParticipantResultsDto gameResult, final String participantName) {
        System.out.println(GAME_RESULT_PREFIX);
        if (gameResult.isSingle()) {
            printSingleResult(gameResult.getSingleResult(participantName));
            return;
        }
        printMultipleResults(gameResult.getParticipantResults());
    }

    public void printSingleResult(final GameResult gameResult) {
        System.out.println(gameResult.getValue());
    }

    public void printMultipleResults(final Map<String, GameResult> gameResults) {
        gameResults.keySet()
                   .stream()
                   .map(participantName -> String.join(
                           GAME_RESULT_DELIMITER,
                           participantName,
                           gameResults.get(participantName)
                                      .getValue())
                   )
                   .forEach(System.out::println);
    }

    public void printExceptionMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    enum LadderSymbol {

        SPACE(" "),
        BAR("-");

        private final String symbol;

        LadderSymbol(final String symbol) {
            this.symbol = symbol;
        }

        public static String findSymbol(final boolean isExist) {
            if (isExist) {
                return BAR.symbol;
            }
            return SPACE.symbol;
        }
    }
}
