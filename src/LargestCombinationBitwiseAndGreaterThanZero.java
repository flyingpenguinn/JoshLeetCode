public class LargestCombinationBitwiseAndGreaterThanZero {
    public int largestCombination(int[] a) {
        int[] c = new int[32];
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 32; ++j) {
                if (((a[i] >> j) & 1) == 1) {
                    ++c[j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            res = Math.max(res, c[i]);
        }
        return res;
    }
}
