import java.util.Random;

public class Car {

    private String name;
    private int position;
    private static final Random random = new Random();

    public Car(String name) {
        validateNotNull(name);
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    private void validateNotNull(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 값이 될 수 없습니다.");
        }
    }

    public int getRandomNumber() {
         int randomNum = random.nextInt(10);

         return randomNum;
    }

    public void movePosition(int randomNumber) {
        if (randomNumber > 3) {
            position++;
        }
    }
}
