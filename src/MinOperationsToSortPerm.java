public class MinOperationsToSortPerm {
    public int minOperations(int[] a) {
        int n = a.length;
        if (n == 1) {
            return 0;
        }

        int res = (int) 2e9;

        if (inc(a)) {
            int k = a[0];

            if (k == 0) {
                res = Math.min(res, 0);
            } else {
                res = Math.min(res, n - k);
                res = Math.min(res, k + 2);
            }
        }

        if (dec(a)) {
            int k = n - 1 - a[0];

            res = Math.min(res, k + 1);
            res = Math.min(res, n - k + 1);
        }

        return res >= (int) 2e9 ? -1 : res;
    }

    private boolean inc(int[] a) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            if (a[j] != (a[i] + 1) % n) {
                return false;
            }
        }

        return true;
    }

    private boolean dec(int[] a) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            if (a[j] != (a[i] - 1 + n) % n) {
                return false;
            }
        }

        return true;
    }
}
