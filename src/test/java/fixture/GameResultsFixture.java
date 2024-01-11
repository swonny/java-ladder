package fixture;

import domain.GameResult;
import domain.GameResults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameResultsFixture {

    private static GameResults gameResults;

    private GameResultsFixture() {
    }

    public static GameResults of(final String... gameResultNames) {
        final List<GameResult> unwrappedGameResults = Arrays.stream(gameResultNames)
                                                            .map(GameResult::new)
                                                            .collect(Collectors.toUnmodifiableList());
        gameResults = new GameResults(unwrappedGameResults);
        return gameResults;
    }

    public static GameResult getGameResult(final String gameResultName) {
        return gameResults.getGameResult(gameResultName);
    }
}
