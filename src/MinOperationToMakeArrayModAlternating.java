public class MinOperationToMakeArrayModAlternating {
    public int minOperations(int[] a, int k) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            a[i] %= k;
        }
        // System.out.println(Arrays.toString(a));
        int res = (int) 1e9;
        for (int x = 0; x < k; ++x) {
            for (int y = 0; y < k; ++y) {
                if (x == y) {
                    continue;
                }
                int cres = 0;
                for (int i = 0; i < n; ++i) {
                    if (i % 2 == 0) {
                        int cur = a[i];
                        int d = Math.abs(x - cur);
                        cres += Math.min(d, k - d);
                    } else {
                        int cur = a[i];
                        int d = Math.abs(y - cur);
                        cres += Math.min(d, k - d);
                    }
                }
                res = Math.min(res, cres);
            }

        }
        return res;
    }
}
