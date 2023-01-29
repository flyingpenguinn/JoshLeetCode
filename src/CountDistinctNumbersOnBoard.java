public class CountDistinctNumbersOnBoard {
    // n % (n - 1) == 1 if n > 2,
    public int distinctIntegers(int n) {
        return n == 1 ? 1 : n - 1;
    }
}
