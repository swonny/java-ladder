package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResults {

    private final List<GameResult> gameResults;

    public GameResults(final List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResults from(final List<String> gameResultNames) {
        return gameResultNames.stream()
                              .map(GameResult::new)
                              .collect(Collectors.collectingAndThen(Collectors.toUnmodifiableList(), GameResults::new));
    }

    public Map<Participant, GameResult> findMatchingResults(final Participants participants) {
        final Map<Participant, Position> participantsDestination = participants.findDestination();
        final Map<Participant, GameResult> participantResults = new HashMap<>();
        participantsDestination.keySet()
                               .forEach(participant ->
                                       participantResults.put(participant, findMatchingResult(participant))
                               );

        return Collections.unmodifiableMap(participantResults);
    }

    private GameResult findMatchingResult(final Participant participant) {
        final int participantXPosition = participant.getX();

        return gameResults.get(participantXPosition);
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }

    public GameResult getGameResult(final String gameResultName) {
        return gameResults.stream()
                          .filter(gameResult -> gameResult.getValue().equals(gameResultName))
                          .findAny()
                          .orElseThrow(() -> new IllegalArgumentException("찾는 게임 결과가 없습니다."));
    }
}
