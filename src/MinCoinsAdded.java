import java.util.Arrays;

public class MinCoinsAdded {
    public int minimumAddedCoins(int[] a, int t) {
        Arrays.sort(a);
        int n = a.length;
        long csum = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            long cur = a[i];
            while (cur - 1 > csum) {
                csum += csum + 1;
                ++res;
                if (csum >= t) {
                    return res;
                }
            }
            csum += cur;
            if (csum >= t) {
                return res;
            }
        }
        while (csum < t) {
            csum += csum + 1;
            ++res;
        }
        return res;
    }
}
