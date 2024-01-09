package domain;

public class ParticipantFactory {

    public static Participant from(final String name) {
        final Participant participant = new Participant(name);
        Participants.add(participant);

        return participant;
    }
}
