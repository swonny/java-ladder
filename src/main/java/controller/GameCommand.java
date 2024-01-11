package controller;

public enum GameCommand {
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
