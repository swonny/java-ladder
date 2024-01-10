package controller;

import controller.dto.LadderDto;
import domain.GameResult;
import domain.Ladder;
import domain.LadderFactory;
import domain.Participant;
import domain.Participants;
import domain.RandomBasedBarGenerateStrategy;
import service.LadderService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        play();
    }

    private LadderService initializeLadderService() {
        final Participants participants = createParticipants();
        final List<GameResult> results = createGameResults();
        final Ladder ladder = createLadder(participants.size());

        return new LadderService(ladder, participants, results);
    }

    private Participants createParticipants() {
        final List<String> participantNames = inputView.readParticipants();

        return participantNames.stream()
                               .map(Participant::new)
                               .collect(Collectors.collectingAndThen(Collectors.toUnmodifiableList(), Participants::new));
    }

    private List<GameResult> createGameResults() {
        return inputView.readResult()
                        .stream()
                        .map(GameResult::new)
                        .collect(Collectors.toUnmodifiableList());
    }

    private Ladder createLadder(final int participantSize) {
        final int ladderHeight = inputView.readLadderHeight();

        return LadderFactory.of(new RandomBasedBarGenerateStrategy(), ladderHeight, participantSize);
    }

    private void printLadder() {
        final Ladder ladder = ladderService.getLadder();
        final List<Participant> participants = ladderService.getParticipants();
        final List<GameResult> results = ladderService.getResults();

        final LadderDto ladderDto = LadderDto.from(ladder);
        outputView.printLadder(ladderDto, participants, results);
    }

    private void play() {
        ladderService.calculateResult();
        while (true) {
            final String participantName = inputView.readParticipantName();
            if (GameCommand.isFinished(participantName)) {
                final Map<Participant, GameResult> gameResults = ladderService.getGameResults();
                outputView.printAll(gameResults);
                break;
            }
            printParticipantResult(ladderService, participantName);
        }
    }

    private void printParticipantResult(final LadderService ladderService, final String participantName) {
        final GameResult result = ladderService.findResult(participantName);
        outputView.printResult(result.getValue());
    }

    private enum GameCommand {
        RUN("run"),
        ALL("all");

        private final String value;

        GameCommand(final String value) {
            this.value = value;
        }

        public static boolean isFinished(final String participantName) {
            return ALL.value.equals(participantName);
        }
    }
}
