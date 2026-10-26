import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximizeSumAfterAtMostKDigitRearrangements {
    public long maximizeSum(int[] a, int k) {
        int n = a.length;
        List<Long> ben = new ArrayList<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            long rea = rearrange(v);
            long diff = rea - v;
            if (diff > 0) {
                ben.add(diff);
            }
            res += v;
        }
        Collections.sort(ben, Collections.reverseOrder());

        for (int i = 0; i < k && i < ben.size(); ++i) {
            res += ben.get(i);
        }
        return res;
    }

    private long rearrange(long v) {
        String sv = String.valueOf(v);
        char[] sc = sv.toCharArray();
        Arrays.sort(sc);
        int res = 0;
        for (int i = sc.length - 1; i >= 0; --i) {
            int cind = sc[i] - '0';
            res = res * 10 + cind;
        }
        return res;
    }
}
