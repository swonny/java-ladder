package domain;

import java.util.List;
import java.util.stream.Collectors;

import static domain.Name.MINIMUM_NAME_LENGTH;

public class Names {

    private final List<Name> names;

    public Names(final List<Name> names) {
        this.names = names;
    }

    public String joining(final String delimiter) {
        return names.stream()
                    .map(Name::getName)
                    .collect(Collectors.joining(delimiter));
    }

    public int calculateMaximumNameSize() {
        return names.stream()
                    .mapToInt(Name::size)
                    .max()
                    .orElse(MINIMUM_NAME_LENGTH);
    }
}
