public class MinCostToMakeAllCharsEqual {
    // couldnt figure out in the contest...
    // actually just 2nd half go from middle to end, first half go from middle to beginning...
    public long minimumCost(String s) {
        return Math.min(solve(s, 1), solve(s, 0));
    }

    private long solve(String s, int t) {
        int n = s.length();
        int ops = 0;
        long res = 0;
        for (int i = n / 2 - 1; i >= 0; --i) {
            int v = s.charAt(i) - '0';
            if ((v ^ ops) == t) {
                continue;
            } else {
                res += i + 1;
                ops ^= 1;
            }
        }
        ops = 0;
        for (int i = n / 2; i < n; ++i) {
            int v = s.charAt(i) - '0';
            if ((v ^ ops) == t) {
                continue;
            } else {
                res += n - i;
                ops ^= 1;
            }
        }
        return res;
    }
}

