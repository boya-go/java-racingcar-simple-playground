import java.util.Random;

public class RandomGenerator {
    Random random = new Random();

    public int getRandomNumber() {
        return random.nextInt(10);
    }

}
