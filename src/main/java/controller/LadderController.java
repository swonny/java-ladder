package controller;

import controller.dto.LadderDto;
import domain.Ladder;
import domain.Participant;
import service.LadderService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LadderController {

    private final InputView inputView;
    private final OutputView outputView;

    public LadderController(final Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
    }

    public void run() {
        final List<Participant> participants = readParticipants();
        final int ladderHeight = readLadderHeight();
        final LadderService ladderService = initializeLadderService(participants, ladderHeight);
    }

    private LadderService initializeLadderService(final List<Participant> participants, final int ladderHeight) {
        final LadderService ladderService = LadderService.of(ladderHeight, participants);
        final Ladder ladder = ladderService.getLadder();
        outputView.printLadder(LadderDto.from(ladder), participants);

        return ladderService;
    }

    private int readLadderHeight() {
        return inputView.readLadderHeight();
    }

    private List<Participant> readParticipants() {
        final List<String> participantNames = inputView.readParticipants();

        return participantNames.stream()
                               .map(Participant::new)
                               .collect(Collectors.toUnmodifiableList());
    }
}
