package service;

import domain.GameResult;
import domain.Ladder;
import domain.Participant;
import domain.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LadderService {

    private static final int START_Y_POSITION = 0;

    private final Ladder ladder;
    private final List<Participant> participants;
    private final List<GameResult> gameResults;

    public LadderService(final Ladder ladder, final List<Participant> participants, final List<GameResult> gameResults) {
        this.ladder = ladder;
        this.participants = new ArrayList<>(participants);
        this.gameResults = gameResults;
    }

    // TODO: 2024/01/09 participant가 위치를 알지 않고, Ladder 또는 LadderService가 알도록 하기
    public Map<Participant, GameResult> makeResult() {
        initializePosition();
        moveParticipants();
        final Map<Participant, GameResult> gameResults = calculateResults();

        return gameResults;
    }

    private void initializePosition() {
        for (int x = 0; x < participants.size(); x++) {
            final Participant participant = participants.get(x);
            // TODO: 2023/12/19 move 메서드를 사용해 setter를 대체해보기
            final Position position = new Position(x, START_Y_POSITION);
            participant.updatePosition(position);
        }
    }

    private void moveParticipants() {
        for (final Participant participant : participants) {
            final Position movedPosition = ladder.move(participant.getPosition());
            participant.updatePosition(movedPosition);
        }
    }

    private Map<Participant, GameResult> calculateResults() {
        final Map<Participant, GameResult> gameResults = new HashMap<>();
        for (final Participant participant : participants) {
            final GameResult matchingResult = findMatchingResult(participant);
            gameResults.put(participant, matchingResult);
        }

        return Collections.unmodifiableMap(gameResults);
    }

    private GameResult findMatchingResult(final Participant participant) {
        final int participantXPosition = participant.getPosition()
                                                    .getX();

        return gameResults.get(participantXPosition);
    }

    public Ladder getLadder() {
        return ladder;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public List<GameResult> getGameResults() {
        return gameResults.stream()
                          .collect(Collectors.toUnmodifiableList());
    }
}
