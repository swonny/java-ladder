package domain.fixture;

import domain.Participant;
import domain.Participants;
import domain.Position;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

public class ParticipantsFixture {

    private static Participants participants;

    private ParticipantsFixture() {
    }

    public static Participants of(final String... participantNames) {
        final List<Participant> unwrappedParticipants = Arrays.stream(participantNames)
                                                     .map(Participant::new)
                                                     .collect(toUnmodifiableList());
        participants = new Participants(unwrappedParticipants);
        return participants;
    }

    public static void move(final String participantName, final Position position) {
        final Participant participant = participants.findParticipant(participantName);
        participant.move(position);
    }

    public static Participant getParticipant(final String name) {
        return participants.findParticipant(name);
    }
}
