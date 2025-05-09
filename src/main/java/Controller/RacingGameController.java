package Controller;

import domain.Car;
import domain.CarRaceGame;
import domain.Generator.RandomNumberGenerator;
import view.inputView;
import view.outputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGameController {

    public static final String DELIMITER = ",";

    public void startRacingGame(){
        String names = inputView.enterCarNames();
        int round = inputView.enterRoundNumber();

        List<Car> cars = Arrays.stream(names.split(DELIMITER))
                .map(name -> new Car(name, new RandomNumberGenerator()))
                .collect(Collectors.toList());

        final CarRaceGame carRaceGame = new CarRaceGame(cars);

        outputView.printGameResultTitle();

        for (int i = 0; i < round; i++) {

            carRaceGame.playOneRound();
            System.out.println();
            outputView.printRoundResult(cars);

        }

        carRaceGame.playRacingGame(round);

        List<String> winnersName = carRaceGame.getWinnerNames();

        outputView.printGameWinners(winnersName);




    }
}
