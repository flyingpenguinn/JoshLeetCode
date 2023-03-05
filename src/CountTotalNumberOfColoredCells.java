public class CountTotalNumberOfColoredCells {
    // or O1 n*n + (n-1*(n-1)
    public long coloredCells(int n) {
        if (n == 1) {
            return 1;
        }
        long cur = 1L;
        long gap = 4;
        for (int i = 2; i <= n; ++i) {
            cur += gap;
            gap += 4;
        }
        return cur;
    }
}
