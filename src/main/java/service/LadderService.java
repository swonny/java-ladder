package service;

import domain.GameResult;
import domain.Ladder;
import domain.Participant;
import domain.Participants;
import domain.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LadderService {

    private final Ladder ladder;
    private final Participants participants;
    private final List<GameResult> results;

    private Map<Participant, GameResult> gameResults;

    public LadderService(final Ladder ladder, final Participants participants, final List<GameResult> results) {
        this.ladder = ladder;
        this.participants = participants;
        this.results = results;
    }

    // TODO: 2024/01/09 participant가 위치를 알지 않고, Ladder 또는 LadderService가 알도록 하기
    public void calculateResult() {
        participants.initializePosition();
        participants.moveParticipants(ladder);
        final Map<Participant, Position> participantDestination = participants.findDestination();
        final Map<Participant, GameResult> matchingResults = findMatchingResults(participantDestination);

        gameResults = matchingResults;
    }

    public Map<Participant, GameResult> findMatchingResults(final Map<Participant, Position> participantsDestination) {
        final Map<Participant, GameResult> gameResults = new HashMap<>();
        for (final Participant participant : participantsDestination.keySet()) {
            final GameResult matchingResult = findMatchingResult(participant);
            gameResults.put(participant, matchingResult);
        }

        return Collections.unmodifiableMap(gameResults);
    }

    private GameResult findMatchingResult(final Participant participant) {
        final int participantXPosition = participant.getPosition()
                                                    .getX();

        return results.get(participantXPosition);
    }

    public GameResult findResult(final String participantName) {
        final Participant participant = participants.findParticipant(participantName);
        return gameResults.get(participant);
    }

    public Ladder getLadder() {
        return ladder;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants.getParticipants());
    }

    public List<GameResult> getResults() {
        return Collections.unmodifiableList(results);
    }

    public Map<Participant, GameResult> getGameResults() {
        return Collections.unmodifiableMap(gameResults);
    }
}
