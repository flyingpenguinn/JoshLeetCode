import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountSmallerElementsWithOppositeParity {
    private int[][] bit;

    private int q(int[] bit, int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    private void u(int[] bit, int i, int d) {
        while (i < bit.length) {
            bit[i] += d;
            i += i & (-i);
        }
    }

    public int[] countSmallerOppositeParity(int[] a) {
        int n = a.length;
        int rank = 1;
        int[] na = Arrays.copyOf(a, n);
        Arrays.sort(na);
        int i = 0;
        Map<Integer, Integer> rm = new HashMap<>();
        while (i < n) {
            int j = i;
            while (j < n && na[i] == na[j]) {
                ++j;
            }
            rm.put(na[i], rank++);
            i = j;
        }
        bit = new int[2][rank];
        int[] res = new int[n];
        for (i = n - 1; i >= 0; --i) {
            int v = a[i];
            int modv = v % 2;
            int rv = rm.get(v);
            int cnt = q(bit[modv ^ 1], rv - 1);
            res[i] = cnt;
            u(bit[modv], rv, 1);
        }
        return res;
    }

    static void main() {
        System.out.println(Arrays.toString(new CountSmallerElementsWithOppositeParity().countSmallerOppositeParity(ArrayUtils.read1d("5,2,4,1,3"))));
    }
}
