import java.util.HashSet;
import java.util.Set;

public class LexiSmallestNegatedPermutationSumsToTarget {
    public int[] lexSmallestNegatedPerm(int n, long target) {
        long sum = n * (n + 1L) / 2;
        if (sum < target) {
            return new int[0];
        }
        long diff = sum - target;
        if (diff % 2 == 1) {
            return new int[0];
        }
        long tsum = diff / 2;
        if (sum < tsum) {
            return new int[0];
        }

        int[] res = new int[n];
        int ri = 0;
        Set<Integer> picked = new HashSet<>();
        int[] cand = new int[n];
        for (int i = 1; i <= n; ++i) {
            cand[i - 1] = i;
        }
        int ti = n - 1;
        while (tsum > 0) {
            while (cand[ti] > tsum) {
                --ti;
            }
            tsum -= cand[ti];
            res[ri++] = -cand[ti];
            //    System.out.println("picking "+cand[ti]);
            picked.add(cand[ti]);
            --ti;
        }
        for (int i = 1; i <= n; ++i) {

            if (!picked.contains(i)) {
                //    System.out.println("picking "+i);
                res[ri++] = i;
            }
        }
        return res;
    }
}
