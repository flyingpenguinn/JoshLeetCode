public class FindPunishmentNumberOfInteger {
    // number too small, not worth a dp
    public int punishmentNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            if (good(String.valueOf(i * i).toCharArray(), i)) {
                res += i * i;
            }
        }
        return res;
    }

    private boolean good(char[] s, int n) {
        return solve(s, n, 0) ;
    }

    private boolean solve(char[] s, int t, int i) {
        int n = s.length;
        if (i == n) {
            return t == 0 ;
        }
        int cur = 0;
        boolean res = false;
        for (int j = i; j < n; ++j) {
            cur = cur * 10 + (s[j] - '0');
            if (cur > t) {
                break;
            }
            boolean later = solve(s, t - cur, j + 1);
            if (later) {
                res = true;
                break;
            }
        }
        return res;
    }
}
