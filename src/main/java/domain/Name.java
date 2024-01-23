package domain;

import java.util.Objects;

import static java.lang.String.format;

public class Name {

    public static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 5;

    private final String name;

    public Name(final String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(final String name) {
        if (isInvalidLength(name)) {
            throw new IllegalArgumentException(format(
                    "입력 가능한 이름은 %d자 이상 %d자 이하입니다.",
                    MINIMUM_NAME_LENGTH, MAXIMUM_NAME_LENGTH
            ));
        }
    }

    private static boolean isInvalidLength(final String name) {
        return name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH;
    }

    public int size() {
        return name.length();
    }

    public boolean contains(final String value) {
        return name.contains(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
