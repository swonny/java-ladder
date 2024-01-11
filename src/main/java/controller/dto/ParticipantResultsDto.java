package controller.dto;

import domain.GameResult;
import domain.ParticipantResults;

import java.util.HashMap;
import java.util.Map;

public class ParticipantResultsDto {

    private static final int SINGLE = 1;
    private static final String ALL = "all";

    private final Map<String, GameResult> participantResults;

    private ParticipantResultsDto(final Map<String, GameResult> participantResults) {
        this.participantResults = participantResults;
    }

    public static ParticipantResultsDto from(final ParticipantResults participantResults, final String participantName) {
        if (isAllParticipants(participantResults, participantName)) {
            return createMultipleResults(participantResults);
        }
        return createSingleResult(participantResults, participantName);
    }

    private static boolean isAllParticipants(final ParticipantResults participantResults, final String participantName) {
        return !participantResults.isPresent(participantName) && participantName.equals(ALL);
    }

    private static ParticipantResultsDto createMultipleResults(final ParticipantResults participantResults) {
        final Map<String, GameResult> gameResults = new HashMap<>();
        participantResults.getParticipantResults()
                          .keySet()
                          .forEach(participant ->
                                  gameResults.put(participant.getName(), participantResults.getResult(participant.getName()))
                          );
        return new ParticipantResultsDto(gameResults);
    }

    private static ParticipantResultsDto createSingleResult(final ParticipantResults participantResults, final String participantName) {
        final Map<String, GameResult> gameResults = new HashMap<>();
        gameResults.put(participantName, participantResults.getResult(participantName));

        return new ParticipantResultsDto(gameResults);
    }

    public boolean isSingle() {
        return participantResults.keySet().size() == SINGLE;
    }

    public GameResult getSingleResult(final String participantName) {
        return participantResults.get(participantName);
    }

    public Map<String, GameResult> getParticipantResults() {
        return participantResults;
    }
}
