public class ZeroFilledSubarray {
    public long zeroFilledSubarray(int[] a) {
        int n = a.length;
        int i = 0;
        long res = 0;
        while (i < n) {
            if (a[i] != 0) {
                ++i;
                continue;
            }
            int j = i;
            while (j < n && a[j] == 0) {
                ++j;
            }
            long dist = j - i;
            res += dist * (dist + 1) / 2L;
            i = j;
        }
        return res;
    }
}
