public class MinOperationsToMakeArrayElementsOneII {
    public int minOperations(int[] a) {
        int n = a.length;
        int flips = 0;
        for (int i = 0; i < n; ++i) {
            int fm = flips % 2;
            int cur = a[i] ^ fm;
            if (cur == 0) {
                ++flips;
            }
        }
        return flips;
    }
}
