package service;

import domain.Ladder;
import domain.Participant;
import domain.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LadderService {

    private static final int START_Y_POSITION = 0;

    private final Ladder ladder;
    private final List<Participant> participants;
    private final HashMap<String, Participant> gameResults;

    public LadderService(final Ladder ladder, final List<Participant> participants, final List<String> gameResults) {
        this.ladder = ladder;
        this.participants = new ArrayList<>(participants);
        this.gameResults = initializeGameResults(gameResults);
    }

    private HashMap<String, Participant> initializeGameResults(final List<String> gameResults) {
        final LinkedHashMap<String, Participant> nonMatchedGameResults = new LinkedHashMap<>();
        gameResults.forEach(gameResult -> nonMatchedGameResults.put(gameResult, null));

        return nonMatchedGameResults;
    }

    // TODO: 2024/01/09 participant가 위치를 알지 않고, Ladder 또는 LadderService가 알도록 하기
    public void play() {
        initializePosition();
        for (final Participant participant : participants) {
            final Position movedPosition = ladder.move(participant.getPosition());
            participant.updatePosition(movedPosition);
        }
    }

    private void initializePosition() {
        for (int x = 0; x < participants.size(); x++) {
            final Participant participant = participants.get(x);
            // TODO: 2023/12/19 move 메서드를 사용해 setter를 대체해보기
            final Position position = new Position(x, START_Y_POSITION);
            participant.updatePosition(position);
        }
    }

    public Ladder getLadder() {
        return ladder;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public List<String> getGameResults() {
        return gameResults.keySet()
                          .stream()
                          .collect(Collectors.toUnmodifiableList());
    }
}
