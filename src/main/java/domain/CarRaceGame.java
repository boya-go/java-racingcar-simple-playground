package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarRaceGame {

    private final List<Car> cars;

    public CarRaceGame(List<Car> cars) {
        this.cars = cars;
        notAllowDuplicatedCarNames();
    }

    private void notAllowDuplicatedCarNames() {
        List<String> carNames = cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());

        Set<String> carNameSet = new HashSet<>(carNames);

        if (carNames.size() != carNameSet.size()) {
            throw new IllegalArgumentException("중복된 자동차 이름은 입력할 수 없습니다.");
        }
    }

    public void validateRoundNumber(int gameRound) {
        if (gameRound < 1 ) {
            throw new IllegalArgumentException("라운드는 한 번 이상 진행되어야 합니다.");
        }
    }

    public void playOneRound() {
        for (Car car : cars) {
            car.movePosition();
        }
    }

    private int getMaxDistance() {
        int maxDistance = cars.stream().mapToInt(Car::getPosition).max().orElseThrow();

        return maxDistance;
    }

    private List<String> getWinners(int maxDistance) {
        List<String> winners = cars.stream().filter(car -> car.getPosition() == maxDistance).map(Car::getName).toList();

        return winners;
    }

    public void playRacingGame(int gameRound) {
        validateRoundNumber(gameRound);

        for (int i=0; i < gameRound;i++) {
            playOneRound();
        }
    }

    public List<String> getWinnerNames() {
        List<String> winners = getWinners(getMaxDistance());

        if (winners == null) {
            throw new IllegalStateException("게임이 실행되지 않았습니다.");
        }
        return winners;
    }
}
