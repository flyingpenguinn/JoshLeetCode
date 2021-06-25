import java.util.Arrays;

public class GameOfNim {
    public boolean nimGame(int[] piles) {
        return Arrays.stream(piles).reduce(0,(x, y)->x^y) != 0;
    }
}
