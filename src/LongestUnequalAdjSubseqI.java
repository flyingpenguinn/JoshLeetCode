import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestUnequalAdjSubseqI {
    // I and II are differentp problems!
    public List<String> getWordsInLongestSubsequence(int n, String[] ws, int[] gs) {
        dp = new Integer[n][3];
        choice = new Integer[n][3];
        return solve(ws, gs, 0, 2, n);
    }

    private List<String> solve(String[] ws, int[] gs, int i, int j, int n) {
        int len = dpsolve(ws, gs, i, j, n);
        List<String> res = findsol(ws, gs, 0, 2, ws.length);
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
            List<String> later = findsol(ws, gs, i + 1, gs[i] ^ 1, n);
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

        if (j == 2 || gs[i] == j) {
            way2 = 1 + dpsolve(ws, gs, i + 1, gs[i] ^ 1, n);
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
}
