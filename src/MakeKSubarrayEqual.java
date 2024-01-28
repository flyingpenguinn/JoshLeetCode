import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MakeKSubarrayEqual {
    // we note that if k=3, then 0, 3, 6... must be equal, so does 1,4,7....
    // focus on k-cycles not the entire array
    // length of the cycle is actually gcd(n, k) so we can actually do it in a simpler way by getting all the gcd len lists and sort
    public long makeSubKSumEqual(int[] a, int k) {
        long res = 0;
        int n = a.length;
        for (int i = 0; i < k; ++i) {
            if (a[i] == 0) {
                // might already be set to 0
                continue;
            }
            List<Integer> cycle = new ArrayList<>();
            for (int j = i; a[j] != 0; j = (j + k) % n) {
                cycle.add(a[j]);
                a[j] = 0;
            }
            //     System.out.println(cycle);
            Collections.sort(cycle);
            int md = cycle.get(cycle.size() / 2);
            for (int ci : cycle) {
                res += Math.abs(ci - md);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MakeKSubarrayEqual().makeSubKSumEqual(ArrayUtils.read1d("9,1,5,10,6"), 2));
    }
}
