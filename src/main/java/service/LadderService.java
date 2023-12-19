package service;

import domain.Ladder;
import domain.Participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LadderService {

    private final Ladder ladder;
    private final List<Participant> participantList;
    private final List<String> gameResults;

    public LadderService(final Ladder ladder, final List<Participant> participantList, final List<String> gameResults) {
        this.ladder = ladder;
        this.participantList = new ArrayList<>(participantList);
        this.gameResults = gameResults;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participantList);
    }

    public List<String> getGameResults() {
        return gameResults.stream()
                          .collect(Collectors.toUnmodifiableList());
    }
}
