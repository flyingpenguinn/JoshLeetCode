import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class AndroidUnlockPatterns {
    String[] invalid = {"", "379", "8", "179", "6", "", "4", "139", "2", "137"};
    String[] pass = {"", "245", "5", "256", "5", "", "5", "458", "5", "568"};
    int[][] dp;

    public int numberOfPatterns(int m, int n) {
        dp = new int[10][1 << 10];
        // use mirror to reduce time
        return 4 * dfs(1, (1 << 1), 1, m, n) + 4 * dfs(2, (1 << 2), 1, m, n) + dfs(5, (1 << 5), 1, m, n);
    }

    // how many with pre == pre and pre len == cur
    int dfs(int pre, int st, int cur, int m, int n) {
        if (dp[pre][st] != 0) {
            return dp[pre][st];
        }
        int r = 0;
        // adding nothing on top of previous result
        if (cur >= m) {
            r++;
        }
        if (cur == n) {
            dp[pre][st] = r;
            return r;
        }
        // or add more
        for (int i = 1; i <= 9; i++) {
            if (i == pre || ((st >> i) & 1) == 1) {
                continue;
            }
            int nst = st | (1 << i);
            int idx = invalid[pre].indexOf(('0' + i));
            if (idx == -1) {
                r += dfs(i, nst, cur + 1, m, n);
            } else {
                int pa = pass[pre].charAt(idx) - '0';
                if (((st >> pa) & 1) == 1) {
                    r += dfs(i, nst, cur + 1, m, n);
                }
            }
        }
        dp[pre][st] = r;
        return r;
    }
}
