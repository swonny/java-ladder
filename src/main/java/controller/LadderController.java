package controller;

import domain.Participant;
import view.InputView;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LadderController {

    private final InputView inputView;

    public LadderController(final Scanner scanner) {
        this.inputView = new InputView(scanner);
    }

    public void run() {
        final List<Participant> participants = readParticipants();
    }

    private List<Participant> readParticipants() {
        final List<String> participantNames = inputView.readParticipants();

        return participantNames.stream()
                               .map(Participant::new)
                               .collect(Collectors.toUnmodifiableList());
    }
}
