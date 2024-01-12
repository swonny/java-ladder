package controller.dto;

import domain.GameResult;
import domain.Name;
import domain.ParticipantResults;

import java.util.HashMap;
import java.util.Map;

public class ParticipantResultsDto {

    private static final int SINGLE = 1;
    private static final String ALL = "all";

    private final Map<Name, GameResult> participantResults;

    private ParticipantResultsDto(final Map<Name, GameResult> participantResults) {
        this.participantResults = participantResults;
    }

    public static ParticipantResultsDto from(final ParticipantResults participantResults, final Name participantName) {
        if (participantName.contains(ALL)) {
            return createMultipleResults(participantResults);
        }
        return createSingleResult(participantResults, participantName);
    }

    private static ParticipantResultsDto createMultipleResults(final ParticipantResults participantResults) {
        final Map<Name, GameResult> gameResults = new HashMap<>();
        participantResults.getParticipantResults()
                          .keySet()
                          .forEach(participant ->
                                  gameResults.put(participant.getName(), participantResults.getResult(participant.getName()))
                          );
        return new ParticipantResultsDto(gameResults);
    }

    private static ParticipantResultsDto createSingleResult(final ParticipantResults results, final Name participantName) {
        final Map<Name, GameResult> gameResults = new HashMap<>();
        gameResults.put(participantName, results.getResult(participantName));

        return new ParticipantResultsDto(gameResults);
    }

    public boolean isSingle() {
        return participantResults.keySet().size() == SINGLE;
    }

    public GameResult getSingleResult(final Name participantName) {
        return participantResults.get(participantName);
    }

    public Map<Name, GameResult> getParticipantResults() {
        return participantResults;
    }
}
