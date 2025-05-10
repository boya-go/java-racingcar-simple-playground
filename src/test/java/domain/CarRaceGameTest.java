package domain;

import domain.Generator.FixedNumberGenerator;
import domain.Generator.NumberGenerator;
import domain.Generator.RandomNumberGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
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

    @Test
    void playRacingGame_참여자모두가우승자인경우_정상출력() {
        List<Car> cars = createCars(
                new FixedNumberGenerator(3, 1, 7, 9),
                new FixedNumberGenerator(0, 4, 2, 8),
                new FixedNumberGenerator(7, 2, 5, 1)
        );

        CarRaceGame game = new CarRaceGame(cars);
        game.playRacingGame(4);

        assertThat(game.getWinnerNames()).containsOnly("차1", "차2", "차3");

    }

    @Test
    void playRacingGame_모든참여자가전진하지못한경우_정상출력() {
        List<Car> cars = createCars(
                new FixedNumberGenerator(3, 0, 2, 1),
                new FixedNumberGenerator(0, 3, 1, 1),
                new FixedNumberGenerator(0, 1, 0, 1)
        );

        CarRaceGame game = new CarRaceGame(cars);
        game.playRacingGame(4);

        assertThat(game.getWinnerNames()).containsOnly("차1", "차2", "차3");

    }

    @Test
    void notAllowDuplicatedCarNames_중복된차이름_예외처리() {

        NumberGenerator generator = new RandomNumberGenerator();

        Car car1 = new Car("차1", generator);
        Car car2 = new Car("차3", generator);
        Car car3 = new Car("차3", generator);

        List<Car> cars = Arrays.asList(car1, car2, car3);

        assertThatThrownBy(() -> new CarRaceGame(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 자동차 이름은 입력할 수 없습니다.");
    }

}
