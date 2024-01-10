package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private static final int START_Y_POSITION = 0;

    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public void initializePosition() {
        for (int x = 0; x < participants.size(); x++) {
            final Participant participant = participants.get(x);
            // TODO: 2023/12/19 move 메서드를 사용해 setter를 대체해보기
            final Position position = new Position(x, START_Y_POSITION);
            participant.updatePosition(position);
        }
    }

    public void moveParticipants(final Ladder ladder) {
        for (final Participant participant : participants) {
            final Position movedPosition = ladder.move(participant.getPosition());
            participant.updatePosition(movedPosition);
        }
    }

    public Participant findParticipant(final String name) {
        return participants.stream()
                           .filter(participant -> participant.hasSameName(name))
                           .findAny()
                           .orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 없습니다."));
    }

    public Map<Participant, Position> findDestination() {
        final Map<Participant, Position> participantsAtDestination = new HashMap<>();
        participants.forEach(participant -> participantsAtDestination.put(participant, participant.getPosition()));

        return participantsAtDestination;
    }

    public int size() {
        return participants.size();
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
