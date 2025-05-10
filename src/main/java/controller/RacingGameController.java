package controller;

import domain.Car;
import domain.CarRaceGame;
import domain.Generator.RandomNumberGenerator;
import view.inputView;
import view.outputView;

import java.util.*;
import java.util.stream.Collectors;

public class RacingGameController {

    public static final String DELIMITER = ",";

    public void startRacingGame(){
        String names = inputView.enterCarNames();

        List<Car> cars = Arrays.stream(names.split(DELIMITER))
                .map(name -> new Car(name, new RandomNumberGenerator()))
                .collect(Collectors.toList());

        final CarRaceGame carRaceGame = new CarRaceGame(cars);

        int round = inputView.enterRoundNumber();

        carRaceGame.validateRoundNumber(round);

        outputView.printGameResultTitle();

        for (int i = 0; i < round; i++) {

            carRaceGame.playOneRound();
            System.out.println();
            outputView.printRoundResult(cars);

        }

        List<String> winnersName = carRaceGame.getWinnerNames();

        outputView.printGameWinners(winnersName);

    }
}
