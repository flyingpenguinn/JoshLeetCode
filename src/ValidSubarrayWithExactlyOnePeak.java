import java.util.ArrayList;
import java.util.List;

public class ValidSubarrayWithExactlyOnePeak {
    public long validSubarrays(int[] a, int k) {
        int n = a.length;
        List<Integer> pks = new ArrayList<>();
        for (int i = 1; i < n - 1; ++i) {
            if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
                pks.add(i);
            }
        }
        int last = -1;
        int pn = pks.size();
        long res = 0;
        for (int i = 0; i < pn; ++i) {
            int ci = pks.get(i);
            int next = i + 1 < pn ? pks.get(i + 1) : n;
            long llen = Math.min(ci - last, k + 1);
            long rlen = Math.min(next - ci, k + 1);
            //  System.out.println(next+" "+llen+" "+rlen);
            long cur = llen * rlen;
            res += cur;
            last = ci;
        }
        return res;
    }
}
