public class NumberOfSubarraysWithEvenProduct {
    public long evenProduct(int[] a) {
        int n = a.length;
        int lasteven = -1;
        long odds = 0; // odd products
        for (int i = 0; i < n; ++i) {
            if (a[i] % 2 == 1) {
                odds += i - lasteven;
            } else {
                lasteven = i;
            }
        }
        return 1L * n * (n + 1) / 2 - odds;
    }
}
