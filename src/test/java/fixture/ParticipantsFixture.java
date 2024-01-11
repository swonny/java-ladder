package fixture;

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
        final List<String> unwrappedParticipants = Arrays.stream(participantNames)
                                                         .collect(toUnmodifiableList());
        participants = Participants.from(unwrappedParticipants);
        return participants;
    }

    public static void move(final String participantName, final Position position) {
        final Participant participant = participants.findParticipant(participantName).get();
        participant.move(position);
    }

    public static Participant getParticipant(final String name) {
        return participants.findParticipant(name).get();
    }
}
