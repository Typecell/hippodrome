import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void whenFirstParamIsNull_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0.1, 0.1));
    }

    @Test
    void whenFirstParamIsNull_thenExceptionMessageIsCorrect() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0.1, 0.1));
        String expected = "Name cannot be null.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void whenFirstParamIsBlank_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("  ", 0.1, 0.1));
    }

    @Test
    void whenFirstParamIsBlank_thenExceptionMessageIsCorrect() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("  ", 0.1, 0.1));
        String expected = "Name cannot be blank.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void whenSecondParamIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1, 0.1));
    }

    @Test
    void whenSecondParamIsNegative_thenExceptionMessageIsCorrect() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1, 0.1));
        String expected = "Speed cannot be negative.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void whenThirdParamIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 0.1, -1));
    }

    @Test
    void whenThirdParamIsNegative_thenExceptionMessageIsCorrect() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 0, -1));
        String expected = "Distance cannot be negative.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void whenSettingName_thenReturnIsCorrect() {
        String expected = "Horsename";
        Horse horse = new Horse(expected, 0.1);
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    void whenSettingSpeed_thenReturnIsCorrect() {
        double expected = 0.5;
        Horse horse = new Horse("Horsename", expected);
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    void whenSettingDistance_thenReturnIsCorrect() {
        double expected = 0.5;
        Horse horse = new Horse("Horsename", 0.1, expected);
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void whenNotSettingThirdParamThenReturnIsZero() {
        double expected = 0.0;
        Horse horse = new Horse("Horsename", 0.1);
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void moveMethodIsCallingGetRandomDouble() {
        Horse horse = new Horse("Horsename", 0.1);

        try (MockedStatic<Horse> staticHorse = Mockito.mockStatic(Horse.class)) {
            horse.move();
            staticHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 5.0, 0.5, 12.5",
            "0.0, 2.0, 0.3, 0.6",
            "100.0, 10.0, 0.9, 109.0"
    })
    void moveMethodSettingDistanceCorrect(double initialDistance, double speed, double mockRandomValue, double expectedDistance) {

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandomValue);

            Horse horse = new Horse("TestHorse", speed, initialDistance);
            horse.move();

            assertEquals(expectedDistance, horse.getDistance(), 0.01);
        }
    }
}