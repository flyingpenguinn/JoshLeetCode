public class LibraryLateFeeCalc {
    public int lateFee(int[] daysLate) {
        int res = 0;
        for (int di : daysLate) {
            if (di == 1) {
                res += di;
            } else if (di >= 2 && di <= 5) {
                res += 2 * di;
            } else if (di > 5) {
                res += 3 * di;
            }
        }
        return res;
    }
}
