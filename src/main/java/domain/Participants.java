package domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private static List<Participant> participants = new ArrayList<>();

    private Participants() {
    }

    public static void add(final Participant participant) {
        participants.add(participant);
    }

    public static Participant findParticipant(final String name) {
        return participants.stream()
                           .filter(participant -> participant.hasSameName(name))
                           .findAny()
                           .orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 없습니다."));
    }
}
