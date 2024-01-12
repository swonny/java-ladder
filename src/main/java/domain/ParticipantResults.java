package domain;

import java.util.Map;

public class ParticipantResults {

    private static final long SINGLE_PARTICIPANT_COUNT = 1;

    private final Map<Participant, GameResult> participantResults;

    public ParticipantResults(final Map<Participant, GameResult> participantResults) {
        this.participantResults = participantResults;
    }

    public boolean isPresent(final Name participantName) {
        final long participantCount = participantResults.keySet()
                                                        .stream()
                                                        .filter(participant -> participant.hasSameName(participantName))
                                                        .count();

        return participantCount >= SINGLE_PARTICIPANT_COUNT;
    }

    public Map<Participant, GameResult> getParticipantResults() {
        return participantResults;
    }

    public GameResult getResult(final Name participantName) {
        return participantResults.keySet()
                                 .stream()
                                 .filter(participant -> participant.hasSameName(participantName))
                                 .map(participantResults::get)
                                 .findAny()
                                 .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}
