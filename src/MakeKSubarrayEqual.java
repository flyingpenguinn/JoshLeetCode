import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MakeKSubarrayEqual {
    // focus on k-cycles not the entire array
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
