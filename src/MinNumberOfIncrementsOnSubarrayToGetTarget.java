public class MinNumberOfIncrementsOnSubarrayToGetTarget {
    // given current watermark, we can add to it, or bank on it
    public int minNumberOperations(int[] a) {
        int res = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1]) {
                res += a[i] - a[i - 1];
            }
        }
        return res;
    }
}
