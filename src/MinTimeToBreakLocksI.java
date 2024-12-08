import java.util.List;

public class MinTimeToBreakLocksI {
    public int findMinimumTime(List<Integer> a, int k) {
        int n = a.size();
        return solve(a, 0, 0, k);
    }

    private int solve(List<Integer> a, int st, int times, int k) {
        int n = a.size();
        if ((st + 1) == (1 << n)) {
            return 0;
        }
        int x = 1 + times * k;
        int res = (int) (1e9);
        for (int i = 0; i < n; ++i) {
            if (((st >> i) & 1) == 0) {
                int taken = (int) Math.ceil(a.get(i) * 1.0 / x);
                int nst = st | (1 << i);
                int later = solve(a, nst, times + 1, k);
                int cres = taken + later;
                res = Math.min(res, cres);
            }
        }
        return res;
    }
}
