package domain;

import java.util.List;

public class CarRaceGame {

    private List<Car> cars;
    private List<String> winners;

    public CarRaceGame(List<Car> cars) {
        this.cars = cars;
    }

    private void validateRoundNumber(int gameRound) {
        if (gameRound < 1 ) {
            throw new IllegalArgumentException("라운드는 한 번 이상 진행되어야 합니다.");
        }
    }

    public void playOneRound() {
        for (Car car : cars) {
            car.movePosition();
        }
    }

//    private void carRacing(int gameRound) {
//        validateRoundNumber(gameRound);
//        for (int i= 0; i < gameRound; i++) {
//            playOneRound();
//        }
//    }

    private int getMaxDistance() {
        int maxDistance = cars.stream().mapToInt(Car::getPosition).max().orElseThrow();

        return maxDistance;
    }

    private List<String> getWinners(int maxDistance) {
        List<String> winners = cars.stream().filter(car -> car.getPosition() == maxDistance).map(Car::getName).toList();

        return winners;
    }

    public void playRacingGame(int gameRound) {
        winners = getWinners(getMaxDistance());
    }

    public List<String> getWinnerNames() {
        if (winners == null) {
            throw new IllegalStateException("게임이 실행되지 않았습니다.");
        }
        return winners;
    }
}
