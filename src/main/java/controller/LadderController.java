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
        final LadderService ladderService = initializeLadderService();
        printLadder(ladderService.getLadder(), ladderService.getParticipants());
    }

    private LadderService initializeLadderService() {
        final List<Participant> participants = readParticipants();
        final int ladderHeight = readLadderHeight();
        return LadderService.of(ladderHeight, participants);
    }

    private List<Participant> readParticipants() {
        final List<String> participantNames = inputView.readParticipants();

        return participantNames.stream()
                               .map(Participant::new)
                               .collect(Collectors.toUnmodifiableList());
    }

    private int readLadderHeight() {
        return inputView.readLadderHeight();
    }

    private void printLadder(final Ladder ladder, final List<Participant> participants) {
        final LadderDto ladderDto = LadderDto.from(ladder);
        outputView.printLadder(ladderDto, participants);
    }
}
