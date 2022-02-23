public class CountEqualAndDivisiblePairsInArray {
    public int countPairs(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (a[i] == a[j] && (i * j) % k == 0) {
                    ++res;
                }
            }
        }
        return res;
    }
}
