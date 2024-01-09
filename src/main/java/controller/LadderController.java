package controller;

import controller.dto.LadderDto;
import domain.Ladder;
import domain.LadderFactory;
import domain.Participant;
import domain.ParticipantFactory;
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

    public LadderController(final Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
    }

    public void run() {
        final LadderService ladderService = initializeLadderService();
        printLadder(ladderService.getLadder(), ladderService.getParticipants(), ladderService.getGameResults());
        play(ladderService);
    }

    private LadderService initializeLadderService() {
        final List<Participant> participants = createParticipants();
        final List<String> results = inputView.readResult();
        final Ladder ladder = createLadder(participants.size());

        return new LadderService(ladder, participants, results);
    }

    private List<Participant> createParticipants() {
        final List<String> participantNames = inputView.readParticipants();

        return participantNames.stream()
                               .map(ParticipantFactory::from)
                               .collect(Collectors.toUnmodifiableList());
    }

    private Ladder createLadder(final int participantSize) {
        final int ladderHeight = inputView.readLadderHeight();

        return LadderFactory.of(new RandomBasedBarGenerateStrategy(), ladderHeight, participantSize);
    }

    private void printLadder(final Ladder ladder, final List<Participant> participants, final List<String> gameResults) {
        final LadderDto ladderDto = LadderDto.from(ladder);
        outputView.printLadder(ladderDto, participants, gameResults);
    }

    private void play(final LadderService ladderService) {
        final Map<Participant, String> gameResults = ladderService.makeResult();
        while (true) {
            final String participantName = inputView.readParticipantName();
            if (GameCommand.isFinished(participantName)) {
                outputView.printAll(gameResults);
                break;
            }
            printParticipantResult(gameResults, participantName);
        }
    }

    private void printParticipantResult(final Map<Participant, String> gameResults, final String participantName) {
        final Participant participant = Participants.findParticipant(participantName);
        final String gameResult = gameResults.get(participant);

        outputView.printResult(gameResult);
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
