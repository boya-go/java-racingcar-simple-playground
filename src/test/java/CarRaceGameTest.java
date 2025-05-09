import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class CarRaceGameTest {

    private List<Car> createCars(NumberGenerator... generators) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < generators.length; i++) {
            cars.add(new Car("차" + (i + 1), generators[i]));
        }
        return cars;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -4})
    void playRacingGame_게임라운드0이하_예외처리(int gameRound) {
        List<Car> cars = createCars(
                new RandomNumberGenerator(),
                new RandomNumberGenerator(),
                new RandomNumberGenerator()
        );

        CarRaceGame game = new CarRaceGame(cars);

        assertThatThrownBy(() -> game.playRacingGame(gameRound))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("라운드는 한 번 이상 진행되어야 합니다.");
    }

    @Test
    void playRacingGame_우승자한명_정상출력() {
        List<Car> cars = createCars(
                new FixedNumberGenerator(9, 6, 4, 2, 5),
                new FixedNumberGenerator(0, 2, 2, 1, 3),
                new FixedNumberGenerator(1, 2, 5, 0, 9)
        );

        CarRaceGame game = new CarRaceGame(cars);
        game.playRacingGame(5);

        assertThat(game.getWinnerNames()).containsOnly("차1");
    }

    @Test
    void playRacingGame_우승자두명_정상출력() {
        List<Car> cars = createCars(
                new FixedNumberGenerator(1, 8, 7),
                new FixedNumberGenerator(0, 9, 2),
                new FixedNumberGenerator(7, 2, 5)
        );

        CarRaceGame game = new CarRaceGame(cars);
        game.playRacingGame(3);

        assertThat(game.getWinnerNames()).containsOnly("차1", "차3");
    }
}
