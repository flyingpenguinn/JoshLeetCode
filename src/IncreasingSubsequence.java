import base.ArrayUtils;

import java.util.*;

public class IncreasingSubsequence {
    public List<List<Integer>> findSubsequences(int[] a) {
        int n = a.length;
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++) {
            last.put(a[i], i);
        }
        List<List<Integer>>[] dp = new ArrayList[n];
        List<List<Integer>> r = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();
            // only deal with unseen js
            Set<Integer> seen = new HashSet<>();
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] <= a[i] && !seen.contains(a[j])) {
                    seen.add(a[j]);
                    List<List<Integer>> prev = dp[j];
                    for (List<Integer> pi : prev) {
                        List<Integer> npi = new ArrayList<>(pi);
                        npi.add(a[i]);
                        dp[i].add(npi);
                    }
                }
            }
            List<Integer> cur = new ArrayList<>();
            cur.add(a[i]);
            dp[i].add(cur);
            // only add the last one of each number
            if (last.get(a[i]) == i) {
                for (List<Integer> di : dp[i]) {
                    if (di.size() > 1) {
                        r.add(di);
                    }
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new IncreasingSubsequence().findSubsequences(ArrayUtils.read1d("7,7,7")));
    }
}
