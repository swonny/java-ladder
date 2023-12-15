package domain;

import static java.lang.String.format;

public class Participant {

    public static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 5;
    private final String name;

    public Participant(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(format("입력 가능한 이름은 최대 %d자 입니다.", MAXIMUM_NAME_LENGTH));
        }
    }

    public String getName() {
        return name;
    }
}
