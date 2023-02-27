package domain;

import factory.LineFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LineTest {

    private static final RandomBasedStrategy randomBasedStrategy = new RandomBasedStrategy();

    @DisplayName("라인의 포인트 개수는 19를 넘을 수 없다.")
    @Test
    void pointNotMoreThan19() {
        // given
        int pointSize = 20;

        // when, then
        assertThatThrownBy(() -> LineFactory.of(pointSize, randomBasedStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("포인트 범위는 0부터 19까지입니다.");
    }

    @DisplayName("라인의 포인트 개수는 0보다 작을 수 없다.")
    @Test
    void pointNotLessThan0() {
        // given
        int pointSize = -1;

        // when, then
        assertThatThrownBy(() -> LineFactory.of(pointSize, randomBasedStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("포인트 범위는 0부터 19까지입니다.");
    }

    @DisplayName("라인의 포인트 개수는 0이상 19이하이다.")
    @ValueSource(ints = {0, 10, 19})
    @ParameterizedTest
    void pointSizeTest(int expectedPointSize) {
        // when
        Line line = LineFactory.of(expectedPointSize, randomBasedStrategy);
        int pointSize = line.getPoints().size();

        // then
        assertThat(pointSize).isEqualTo(expectedPointSize);
    }

    @DisplayName("사다리의 포인트가 true인 지점은 연속될 수 없다.")
    @Test
    void pointNotContinuous() {
        // given
        int pointSize = 5;
        Line line = LineFactory.of(pointSize, randomBasedStrategy);

        // when, then
        for (int pointIndex = 0; pointIndex < pointSize - 1; pointIndex++) {
            Point left = line.getPointAt(pointIndex);
            Point right = line.getPointAt(pointIndex + 1);
            assertThat(left.isExist() && right.isExist()).isFalse();
        }
    }

    @DisplayName("사다리가의 포인트가 true인 지점이 연속되어 있을 경우 예외 처리 한다.")
    @ParameterizedTest
    @MethodSource("providePoints")
    void validatePointExistContinuous(List<Point> points) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Line(points))
                .withMessageContaining("사다리는 같은 라인에서 연속되는 포인트를 가질 수 없습니다.");
    }

    private static Stream<Arguments> providePoints() {
        List<Point> pointsOfTwo = List.of(Point.EXIST, Point.EXIST);
        List<Point> pointsOfTen = Stream.generate(() -> Point.NOT_EXIST)
                .limit(8).collect(Collectors.toList());
        pointsOfTen.add(Point.EXIST);
        pointsOfTen.add(Point.EXIST);
        List<Point> pointsOfNineteen = Stream.generate(() -> Point.NOT_EXIST).limit(17).collect(Collectors.toList());
        pointsOfNineteen.add(Point.EXIST);
        pointsOfNineteen.add(Point.EXIST);
        return Stream.of(
                Arguments.of(pointsOfTwo),
                Arguments.of(pointsOfTen),
                Arguments.of(pointsOfNineteen)
        );
    }

}
