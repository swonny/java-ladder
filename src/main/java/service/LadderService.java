package service;

import domain.GameResult;
import domain.GameResults;
import domain.Ladder;
import domain.Participant;
import domain.ParticipantResults;
import domain.Participants;

import java.util.Collections;
import java.util.List;

public class LadderService {

    private final Ladder ladder;
    private final Participants participants;
    private final GameResults gameResults;

    public LadderService(final Ladder ladder, final Participants participants, final GameResults gameResults) {
        this.ladder = ladder;
        this.participants = participants;
        this.gameResults = gameResults;
    }

    public ParticipantResults calculateResult() {
        participants.initializePosition();
        participants.moveParticipants(ladder);

        return new ParticipantResults(gameResults.findMatchingResults(participants));
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
}
