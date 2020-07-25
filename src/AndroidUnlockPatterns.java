import java.util.*;

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

class AndroidUnlockBacktrack {
    // n! in backtracking
    private int count = 0;

    public int numberOfPatterns(int m, int n) {
        Set<Integer> start = new HashSet<>();
        start.add(1);
        dfs(1, start, m, n);
        start.remove(1);
        start.add(2);
        dfs(2, start, m, n);
        start.remove(2);
        count *= 4;
        start.add(5);
        dfs(5, start, m, n);
        return count;
    }

    // standing at i, do next. set contains visited ones up to i inclusive
    private void dfs(int i, Set<Integer> set, int m, int n) {

        if (set.size() >= m && set.size() <= n) {
            count++;
        }
        if (set.size() == n) {
            return;
        }
        for (int j = 1; j <= 9; j++) {
            if (!set.contains(j) && allowed(set, i, j)) {
                set.add(j);
                dfs(j, set, m, n);
                set.remove(j);
            }
        }
    }


    private boolean allowed(Set<Integer> set, int i, int j) {
        if (i > j) {
            return allowed(set, j, i);
        }
        // i always <j
        if ((i == 1 || i == 4 || i == 7) && j == i + 2) {
            return set.contains(i + 1);
        }
        if ((i == 1 || i == 2 || i == 3) && j == i + 6) {
            return set.contains(i + 3);
        }
        if (i == 3 && j == 7 || i == 1 && j == 9) {
            return set.contains(5);
        }
        return true;
    }
}