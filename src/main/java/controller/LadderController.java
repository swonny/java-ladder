package controller;

import controller.dto.LadderDto;
import controller.dto.ParticipantResultsDto;
import domain.GameResult;
import domain.GameResults;
import domain.Ladder;
import domain.LadderFactory;
import domain.Participant;
import domain.ParticipantResults;
import domain.Participants;
import domain.RandomBasedBarGenerateStrategy;
import service.LadderService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class LadderController {

    private final InputView inputView;
    private final OutputView outputView;

    private LadderService ladderService;

    public LadderController(final Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
    }

    public void run() {
        ladderService = initializeLadderService();
        printLadder();

        final ParticipantResults results = ladderService.calculateResult();
        printResult(results);
    }

    private LadderService initializeLadderService() {
        final Participants participants = readUntilValidInput(() -> Participants.from(inputView.readParticipants()));
        final GameResults results = readUntilValidInput(() -> GameResults.from(inputView.readGameResults()));
        final Ladder ladder = readUntilValidInput(() -> createLadder(participants.size()));

        return new LadderService(ladder, participants, results);
    }

    private <T> T readUntilValidInput(final Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (final IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return readUntilValidInput(inputReader);
        }
    }

    private Ladder createLadder(final int participantSize) {
        final int ladderHeight = inputView.readLadderHeight();

        return LadderFactory.of(new RandomBasedBarGenerateStrategy(), ladderHeight, participantSize);
    }

    private void printLadder() {
        final LadderDto ladderDto = LadderDto.from(ladderService.getLadder());
        final Participants participants = ladderService.getParticipants();
        final List<GameResult> results = ladderService.getGameResults();

        outputView.printLadder(ladderDto, participants, results);
    }

    private void printResult(final ParticipantResults results) {
        GameCommand gameCommand = GameCommand.RUN;
        while (gameCommand.isRunning()) {
            final String participantName = inputView.readParticipantName();
            printParticipantResult(results, participantName);
            gameCommand = GameCommand.from(participantName);
        }
    }

    private void printParticipantResult(final ParticipantResults results, final String participantName) {
        final ParticipantResultsDto participantResults = ParticipantResultsDto.from(results, participantName);
        outputView.printResult(participantResults, participantName);
    }

    private enum GameCommand {
        RUN("run"),
        STOP("all");

        private final String value;

        GameCommand(final String value) {
            this.value = value;
        }

        public static GameCommand from(final String input) {
            if (input.equals(STOP.value)) {
                return STOP;
            }
            return RUN;
        }

        public boolean isRunning() {
            return this.equals(RUN);
        }
    }
}
