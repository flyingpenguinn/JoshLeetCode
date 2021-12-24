public class ElementsInArrayAfterRemovingAndReplacing {
    public int[] elementInNums(int[] a, int[][] qs) {
        int qn = qs.length;
        int n = a.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int t = qs[i][0];
            int index = qs[i][1];
            int rt = t % (2 * n);
            if (rt == n) {
                res[i] = -1;
            } else if (rt < n) {
                int rlen = n - rt;
                if (index >= rlen) {
                    res[i] = -1;
                } else {
                    res[i] = a[index + rt];
                }
            } else {
                int rlen = rt - n;
                if (index >= rlen) {
                    res[i] = -1;
                } else {
                    res[i] = a[index];
                }
            }
        }
        return res;
    }
}
