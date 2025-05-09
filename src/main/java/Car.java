import java.util.Random;

public class Car {

    private String name;
    private int position;
    private final NumberGenerator generator;
    private static final int MOVE_THRESHOLD = 4;


    public Car(String name, NumberGenerator generator) {
        validateNotNull(name);
        this.name = name;
        this.position = 0;
        this.generator = generator;
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

    public void movePosition() {
        if (generator.generate() >= MOVE_THRESHOLD ) {
            position++;
        }
    }
}
