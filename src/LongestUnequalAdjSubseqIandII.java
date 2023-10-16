import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestUnequalAdjSubseqIandII {
    private int limit = -1;

    public List<String> getWordsInLongestSubsequence(int n, String[] ws, int[] gs) {
        limit = n + 1;
        dp = new Integer[n][limit];
        choice = new Integer[n][limit];
        return solve(ws, gs, 0, n, n);
    }

    private List<String> solve(String[] ws, int[] gs, int i, int j, int n) {
        int len = dpsolve(ws, gs, i, j, n);
        List<String> res = findsol(ws, gs, 0, n, ws.length);
        Collections.reverse(res);
        return res;
    }

    private List<String> findsol(String[] ws, int[] gs, int i, int j, int n) {
        if (i == n) {
            return new ArrayList<>();
        }
        if (choice[i][j] == 1) {
            return findsol(ws, gs, i + 1, j, n);
        } else {
            String cur = ws[i];
            List<String> later = findsol(ws, gs, i + 1, i, n);
            later.add(cur);
            return later;
        }
    }

    private Integer[][] dp;
    private Integer[][] choice;

    private int dpsolve(String[] ws, int[] gs, int i, int j, int n) {
        if (i == n) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int way1 = dpsolve(ws, gs, i + 1, j, n);
        int way2 = 0;

        if (j == n || (gs[i] != gs[j] && good(ws[i], ws[j]))) {
            way2 = 1 + dpsolve(ws, gs, i + 1, i, n);
        }
        int res = 0;
        if (way1 >= way2) {
            choice[i][j] = 1;
            res = way1;
        } else {
            choice[i][j] = 2;
            res = way2;
        }
        dp[i][j] = res;
        return res;
    }

    private boolean good(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                ++diff;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestUnequalAdjSubseqIandII().getWordsInLongestSubsequence(3, new String[]{"e", "a", "b"}, ArrayUtils.read1d("0,0,1")));
    }

}
