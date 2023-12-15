import controller.LadderController;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final LadderController ladderController = new LadderController(scanner);
        ladderController.run();

        scanner.close();
    }
}
