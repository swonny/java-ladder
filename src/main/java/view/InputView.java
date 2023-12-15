package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String PARTICIPANT_DELIMITER = ",";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readParticipants() {
        final String participants = scanner.next();

        return Arrays.stream(participants.split(PARTICIPANT_DELIMITER))
                     .collect(Collectors.toUnmodifiableList());
    }
}
