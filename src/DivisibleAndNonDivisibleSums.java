public class DivisibleAndNonDivisibleSums {
    public int differenceOfSums(int n, int m) {
        int v1 = 0;
        int v2 = 0;
        for (int i = 1; i <= n; ++i) {
            if (i % m != 0) {
                v1 += i;
            } else {
                v2 += i;
            }
        }
        return v1 - v2;
    }
}
