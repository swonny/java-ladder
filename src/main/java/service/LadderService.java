package service;

import domain.Ladder;
import domain.LadderFactory;
import domain.Participant;
import domain.RandomBasedBarGenerateStrategy;

import java.util.Collections;
import java.util.List;

public class LadderService {

    private final Ladder ladder;
    private final List<Participant> participantList;

    private LadderService(final Ladder ladder, final List<Participant> participantList) {
        this.ladder = ladder;
        this.participantList = participantList;
    }

    public static LadderService of(final int ladderHeight, final List<Participant> participants) {
        final Ladder ladder = LadderFactory.of(new RandomBasedBarGenerateStrategy(), ladderHeight, participants.size());

        return new LadderService(ladder, participants);
    }

    public Ladder getLadder() {
        return ladder;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participantList);
    }
}
