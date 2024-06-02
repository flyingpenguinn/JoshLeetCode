import java.util.HashSet;
import java.util.Set;

public class FindSubarrayBitwiseAndClosestToK {
    public int minimumDifference(int[] a, int k) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        int res = (int) 1e9 + 10;
        for (int i = 0; i < n; ++i) {
            Set<Integer> cur = new HashSet<>();
            for (int si : set) {
                cur.add(a[i] & si);
            }
            cur.add(a[i]);
            //  System.out.println(i+" "+cur);
            for (int ci : cur) {
                res = Math.min(res, Math.abs(k - ci));
            }
            set = cur;
        }
        return res;
    }
}
