package service;

import domain.Ladder;
import domain.Participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LadderService {

    private final Ladder ladder;
    private final List<Participant> participantList;

    public LadderService(final Ladder ladder, final List<Participant> participantList) {
        this.ladder = ladder;
        this.participantList = new ArrayList<>(participantList);
    }

    public Ladder getLadder() {
        return ladder;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participantList);
    }
}
