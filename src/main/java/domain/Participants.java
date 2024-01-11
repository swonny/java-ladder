package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Participants {

    private static final int START_Y_POSITION = 0;

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<String> participantNames) {
        return participantNames.stream()
                               .map(Participant::new)
                               .collect(Collectors.collectingAndThen(Collectors.toUnmodifiableList(), Participants::new));
    }

    public void initializePosition() {
        for (int x = 0; x < participants.size(); x++) {
            final Participant participant = participants.get(x);
            // TODO: 2024/01/10 move 메서드를 사용해 setter 대체해보기
            final Position position = new Position(x, START_Y_POSITION);
            participant.move(position);
        }
    }

    public void moveParticipants(final Ladder ladder) {
        for (final Participant participant : participants) {
            final Position movedPosition = ladder.move(participant.getPosition());
            participant.move(movedPosition);
        }
    }

    public Optional<Participant> findParticipant(final String name) {
        return participants.stream()
                           .filter(participant -> participant.hasSameName(name))
                           .findAny();
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
