import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMaxNumOfNonIntersectingSubstrings {
    private List[] next = new ArrayList[26];
    private int[] dp;

    public int maxSubstrings(String s) {
        int n = s.length();
        dp = new int[n];
        Arrays.fill(dp, -1);
        for (int i = 0; i < 26; ++i) {
            next[i] = new ArrayList();
        }
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            next[cind].add(i);
        }
        return solve(s, 0);
    }

    private int solve(String s, int i) {
        int n = s.length();
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int way1 = solve(s, i + 1);
        int cind = s.charAt(i) - 'a';
        List<Integer> nexts = next[cind];
        int pos = binary(nexts, i + 3);
        int way2 = 0;
        if (pos >= 0 && pos < nexts.size()) {
            way2 = 1 + solve(s, nexts.get(pos) + 1);
        }
        int res = Math.max(way1, way2);
        dp[i] = res;
        return res;
    }

    private int binary(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
