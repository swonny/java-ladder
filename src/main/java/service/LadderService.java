package service;

import domain.GameResult;
import domain.GameResults;
import domain.Ladder;
import domain.Participant;
import domain.Participants;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LadderService {

    private final Ladder ladder;
    private final Participants participants;
    private final GameResults gameResults;

    private Map<Participant, GameResult> participantResults;

    public LadderService(final Ladder ladder, final Participants participants, final GameResults gameResults) {
        this.ladder = ladder;
        this.participants = participants;
        this.gameResults = gameResults;
    }

    // TODO: 2024/01/09 participant가 위치를 알지 않고, Ladder 또는 LadderService가 알도록 하기
    public void calculateResult() {
        participants.initializePosition();
        participants.moveParticipants(ladder);
        participantResults = gameResults.findMatchingResults(participants);
    }

    public GameResult findResult(final String participantName) {
        final Participant participant = participants.findParticipant(participantName);
        return participantResults.get(participant);
    }

    public Ladder getLadder() {
        return ladder;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants.getParticipants());
    }

    public List<GameResult> getGameResults() {
        return Collections.unmodifiableList(gameResults.getGameResults());
    }

    public Map<Participant, GameResult> getParticipantResults() {
        return Collections.unmodifiableMap(participantResults);
    }
}
