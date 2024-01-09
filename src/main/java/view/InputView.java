package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String PARTICIPANT_DELIMITER = ",";
    private static final String GAME_RESULT_DELIMITER = ",";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public int readLadderHeight() {
        System.out.println("최대 사다리 높이는 몇 개인가요?");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<String> readParticipants() {
        System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
        final String participants = scanner.nextLine();

        return Arrays.stream(participants.split(PARTICIPANT_DELIMITER))
                     .collect(Collectors.toUnmodifiableList());
    }

    public List<String> readResult() {
        System.out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
        final String results = scanner.nextLine();

        return Arrays.stream(results.split(GAME_RESULT_DELIMITER))
                     .collect(Collectors.toUnmodifiableList());
    }

    public String readParticipantName() {
        System.out.println();
        System.out.println("결과를 보고 싶은 사람은?");
        return scanner.nextLine();
    }
}
