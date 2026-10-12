import java.util.List;

public class ApplyOperationsToMaximizeSquareSum {
    // square sum of a|b and a&b always bigger than plain square sum
    public int maxSum(List<Integer> a, int k) {
        int[] cnt = new int[32];
        int n = a.size();
        for (int i = 0; i < n; ++i) {
            int v = a.get(i);
            for (int j = 0; j < 32; ++j) {
                if (((v >> j) & 1) == 1) {
                    ++cnt[j];
                }
            }
        }
        long res = 0;
        long mod = (long) 1e9 + 7;
        for (int i = 0; i < k; ++i) {
            long cnum = 0;
            for (int j = 0; j < 32; ++j) {
                if (cnt[j] > 0) {
                    cnum |= (1 << j);
                    --cnt[j];
                }
            }
            res += cnum * cnum;
            res %= mod;
        }
        return (int) res;
    }
}
