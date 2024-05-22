import java.util.TreeSet;

public class SpecialArrayII {
    // can also use prefix sum to make this On
    public boolean[] isArraySpecial(int[] a, int[][] qs) {
        int n = a.length;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i + 1 < n; ++i) {
            if (a[i] % 2 == a[i + 1] % 2) {
                ts.add(i);
            }
        }
        boolean[] res = new boolean[qs.length];
        for (int i = 0; i < qs.length; ++i) {
            int[] q = qs[i];
            int s = q[0];
            int e = q[1];
            Integer next = ts.ceiling(s);
            if (next != null && next <= e - 1) {
                res[i] = false;
            } else {
                res[i] = true;
            }
        }
        return res;
    }
}
