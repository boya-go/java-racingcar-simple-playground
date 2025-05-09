import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarTest {

    @Test
    void Car_객체생성() {

        // given
        NumberGenerator generator = new RandomNumberGenerator();
        Car car = new Car("붕붕이", generator);

        // when
        assertThat(car.getName()).isEqualTo("붕붕이");
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void Car_이름빈값일경우_예외처리(String name) {

        NumberGenerator generator = new RandomNumberGenerator();

        assertThatThrownBy(() -> new Car(name,generator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 빈 값이 될 수 없습니다.");
    }

    @Test
    void getRandomNumber_랜덤숫자기준충족() {

        NumberGenerator generator = new RandomNumberGenerator();

        Car car = new Car("차1",generator);
        assertThat(generator.generate())
                .isGreaterThanOrEqualTo(   0)
                .isLessThanOrEqualTo(   9);
    }

    @Test
    void movePosition_랜덤숫자4이상일때_1이동() {

        NumberGenerator generator = new FixedNumberGenerator(5);

        // given
        Car car = new Car("차2", generator);

        // when
        car.movePosition();

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    void movePosition_랜덤숫자3이하일때_정지() {

        // given
        NumberGenerator generator = new FixedNumberGenerator(2);
        Car car = new Car("차3", generator);

        // when
        car.movePosition();

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }
}

