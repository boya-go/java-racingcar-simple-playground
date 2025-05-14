package domain;

import domain.generator.RandomNumberGenerator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarNameParser {

    public static final String CAR_NAME_DELIMITER = ",";

    public static List<Car> parseCarName(String carNames) {
        return Arrays.stream(carNames.split(CAR_NAME_DELIMITER))
                .map(name -> new Car(name, new RandomNumberGenerator()))
                .collect(Collectors.toList());
    }
}
