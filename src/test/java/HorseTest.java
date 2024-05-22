import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    // test constructor Horse()

    @Test
    void shouldThrowExceptionIfNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1.0, 1.0);
        });
    }

    @Test
    void shouldEqualsExceptionMessageIfNameIsNull() {
        try {
            new Horse(null, 1.0, 1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t \n  ", "\t\t", "\n", "   "})
    void shouldThrowExceptionIfNameIsBlanc(String s) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(s, 1.0, 1.0);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t \n  ", "\t\t", "\n", "   "})
    void shouldEqualsExceptionMessageIfNameIsBlanc(String s) {
        try {
            new Horse(s, 1.0, 1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    void shouldThrowExceptionIfSpeedIsNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", -1.0, 1.0);
        });
    }

    @Test
    void shouldEqualsExceptionMessageIfSpeedIsNegativeNumber() {
        String message = null;
        try {
            new Horse("name", -1.0, 1.0);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Speed cannot be negative.", message);
    }

    @Test
    void shouldThrowExceptionIfDistanceIsNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 1.0, -1.0);
        });
    }

    @Test
    void shouldEqualsExceptionMessageIfDistanceIsNegativeNumber() {
        String message = null;
        try {
            new Horse("name", 1.0, -1.0);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Distance cannot be negative.", message);
    }

    // test getters

    @Test
    void shouldEqualsWithGetNameResult() throws NoSuchFieldException, IllegalAccessException {
        String n = "testString";
        Horse horse = new Horse(n, 1.0, 1.0);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);

        assertEquals(n, nameValue);
    }

    @Test
    void shouldEqualsWithGetSpeedResult() throws NoSuchFieldException, IllegalAccessException {
        double s = 5.0;
        Horse horse = new Horse("name", s, 1.0);
        Field speed = Horse.class.getDeclaredField("speed");
        speed.setAccessible(true);
        double speedValue = (double) speed.get(horse);

        assertEquals(s, speedValue);
    }

    @Test
    void shouldEqualsWithGetDistanceResultThreeParamConstructor() throws NoSuchFieldException, IllegalAccessException {
        double d = 5.0;
        Horse horse = new Horse("name", 1.0, d);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        double distanceValue = (double) distance.get(horse);

        assertEquals(d, distanceValue);
    }

    @Test
    void shouldEqualsWithGetDistanceResultTwoParamConstructor() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("name", 1.0);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        double distanceValue = (double) distance.get(horse);

        assertEquals(0, distanceValue);
    }

    // test move()

    @Test
    void shouldReturnTrueIfMoveInvokesGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            new Horse("name", 1.0, 1.0).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.2,0.5,0.9,1.0,999.999,0.0})
    void shouldEqualsWithDistance(double random) {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("qwerty", 31, 283);
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(283 + 31 * random, horse.getDistance());
        }
    }
}