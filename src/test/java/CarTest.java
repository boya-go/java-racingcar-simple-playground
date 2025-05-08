import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarTest {

    @Test
    void Car_객체생성() {

        // given
        Car car = new Car("붕붕이");

        // when
        assertThat(car.getName()).isEqualTo("붕붕이");
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void Car_이름빈값일경우_예외처리(String name) {

        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 빈 값이 될 수 없습니다.");
    }

    @Test
    void getRandomNumber_랜덤숫자기준충족() {

        Car car = new Car("차1");
        assertThat(car.getRandomNumber())
                .isGreaterThanOrEqualTo(   0)
                .isLessThanOrEqualTo(   9);
    }

    @Test
    void movePosition_랜덤숫자4이상일때_1이동() {

        // given
        Car car = new Car("차2");

        // when
        car.movePosition(5);

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    void movePosition_랜덤숫자3이하일때_정지() {

        // given
        Car car = new Car("차3");

        // when
        car.movePosition(2);

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }
}

