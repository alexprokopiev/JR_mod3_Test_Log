import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    // test constructor Hippodrome()

    @Test
    void shouldThrowExceptionIfHorsesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }

    @Test
    void shouldEqualsExceptionMessageIfHorsesIsNull() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }

    }

    @Test
    void shouldThrowExceptionIfHorsesIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
    }

    @Test
    void shouldEqualsExceptionMessageIfHorsesIsEmpty() {
        String message = null;
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    // test getHorses()

    @Test
    void shouldReturnTrueIfSameHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("name" + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    // test move()

    @Test
    void shouldInvokeMoveOfEveryHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    // test getWinner()

    @Test
    void shouldEqualsWithGetWinnerResult() {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4, 2.4),
                new Horse("Ace of Spades", 2.5, 2.5),
                new Horse("Zephyr", 2.6, 2.6),
                new Horse("Blaze", 2.7, 2.7),
                new Horse("Lobster", 2.8, 2.8),
                new Horse("Pegasus", 2.9, 2.9),
                new Horse("Cherry", 3, 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        assertSame(horses.get(6), hippodrome.getWinner());
    }
}