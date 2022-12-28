public class MaximizeEnemyFortsCanBeCaptures {
    public int captureForts(int[] a) {
        int n = a.length;
        int lastm1 = -1;
        int last1 = -1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cur = 0;
            if (a[i] == 0) {
                continue;
            } else if (a[i] == 1) {
                if (lastm1 != -1) {
                    cur = i - lastm1 - 1;
                }
                last1 = i;
                lastm1 = -1; // dont forget this
            } else {
                if (last1 != -1) {
                    cur = i - last1 - 1;
                }
                lastm1 = i;
                last1 = -1;
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
