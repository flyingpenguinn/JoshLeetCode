import java.util.HashMap;
import java.util.Map;

public class SmallestUniqueSubArray {
    // rolling hash double hash
    public int smallestUniqueSubarray(int[] a) {
        int n = a.length;
        int l = 1;
        int u = n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (isgoodlen(a, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;

    }

    long Mod1 = 1_000_000_007L;
    long Mod2 = 1_000_000_009L;
    long Base1 = 100_003L;
    long Base2 = 100_019L;


    protected boolean isgoodlen(int[] a, int k) {
        if(k==0){
            return true;
        }
        Map<Long, Integer> sa = new HashMap<>();
        long sum1 = 0;
        long sum2 = 0;
        int na = a.length;
        long baseminus1 = 1;
        long baseminus2 = 1;
        for (int i = 0; i < na; i++) {
            sum1 = sum1 * Base1 + a[i];
            sum1 %= Mod1;
            sum2 = sum2 * Base2 + a[i];
            sum2 %= Mod2;
            long sum = sum1 << 32 ^ sum2;
            int head = i - k + 1;
            if (head >= 0) {

                sa.put(sum, sa.getOrDefault(sum, 0) + 1);
                sum1 = (sum1- baseminus1 * a[head]) % Mod1;
                if (sum1 < 0) {
                    sum1 += Mod1;
                }
                sum2 = (sum2- baseminus2 * a[head]) % Mod2;
                if (sum2 < 0) {
                    sum2 += Mod2;
                }
            } else {
                baseminus1 *= Base1;
                baseminus1 %= Mod1;

                baseminus2 *= Base2;
                baseminus2 %= Mod2;
            }
        }

        for (int v : sa.values()) {
            if (v == 1) {
                return true;
            }
        }
        return false;
    }
}
