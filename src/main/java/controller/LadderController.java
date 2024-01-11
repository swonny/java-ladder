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

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableList;

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
        final Participants participants = createParticipants();
        final GameResults results = createGameResults();
        final Ladder ladder = createLadder(participants.size());

        return new LadderService(ladder, participants, results);
    }

    private Participants createParticipants() {
        return inputView.readParticipants()
                        .stream()
                        .map(Participant::new)
                        .collect(collectingAndThen(toUnmodifiableList(), Participants::new));
    }

    private GameResults createGameResults() {
        return inputView.readResult()
                        .stream()
                        .map(GameResult::new)
                        .collect(collectingAndThen(toUnmodifiableList(), GameResults::new));
    }

    private Ladder createLadder(final int participantSize) {
        final int ladderHeight = inputView.readLadderHeight();

        return LadderFactory.of(new RandomBasedBarGenerateStrategy(), ladderHeight, participantSize);
    }

    private void printLadder() {
        final LadderDto ladderDto = LadderDto.from(ladderService.getLadder());
        final List<Participant> participants = ladderService.getParticipants();
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
