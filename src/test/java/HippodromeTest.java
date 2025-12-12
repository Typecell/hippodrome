import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void whenParamIsNull_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void whenParamIsNull_thenExceptionMessageIsCorrect() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String expected = "Horses cannot be null.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void whenParamIsEmptyList_thenThrowException() {
        List<Horse> list = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
    }

    @Test
    void whenParamIsEmptyList_thenExceptionMessageIsCorrect() {
        List<Horse> list = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
        String expected = "Horses cannot be empty.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void getHorsesReturningSameObjects() {
        List<Horse> expected = List.of(
                new Horse("Horse1", 0.1),
                new Horse("Horse2", 0.2),
                new Horse("Horse3", 0.3),
                new Horse("Horse4", 0.4),
                new Horse("Horse5", 0.5),
                new Horse("Horse6", 0.6),
                new Horse("Horse7", 0.7),
                new Horse("Horse8", 0.7),
                new Horse("Horse9", 0.7),
                new Horse("Horse10", 0.7),
                new Horse("Horse11", 0.1),
                new Horse("Horse12", 0.2),
                new Horse("Horse13", 0.3),
                new Horse("Horse14", 0.4),
                new Horse("Horse15", 0.5),
                new Horse("Horse16", 0.6),
                new Horse("Horse17", 0.7),
                new Horse("Horse18", 0.7),
                new Horse("Horse19", 0.7),
                new Horse("Horse20", 0.7),
                new Horse("Horse20", 0.7),
                new Horse("Horse21", 0.1),
                new Horse("Horse22", 0.2),
                new Horse("Horse23", 0.3),
                new Horse("Horse24", 0.4),
                new Horse("Horse25", 0.5),
                new Horse("Horse26", 0.6),
                new Horse("Horse27", 0.7),
                new Horse("Horse28", 0.7),
                new Horse("Horse29", 0.7),
                new Horse("Horse30", 0.7)
        );

        Hippodrome hippodrome = new Hippodrome(expected);
        List<Horse> actual = hippodrome.getHorses();

        assertEquals(expected, actual);
    }

    @Test
    void moveCallingOnEachHorse() {
        List<Horse> horses = List.of(
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class),
                Mockito.mock(Horse.class)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void winnerHorseDefinedCorrectly() {
        List<Horse> horses = List.of(
                new Horse("Horse1", 0.1, 3),
                new Horse("Horse2", 0.2, 1),
                new Horse("Horse3", 0.3),
                new Horse("Horse4", 0.4, 5),
                new Horse("Horse5", 0.5, 2)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse expected = horses.get(3);
        Horse actual = hippodrome.getWinner();

        assertEquals(expected, actual);
    }
}