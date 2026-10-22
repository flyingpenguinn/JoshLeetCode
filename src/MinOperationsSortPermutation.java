public class MinOperationsSortPermutation {
    private int treat(int[] a) {
        if (sorted(a)) {
            return 0;
        }
        int way1 = way1(a);
        int way2 = way2(a);
        int cmin = Math.min(way1, way2);
        return cmin;
    }

    public int minOperations(int[] a) {
        int n = a.length;
        int t1 = treat(a);
        rev(a);
        int t2 = treat(a) + 1;
        int rt = Math.min(t1, t2);
        return rt >= Max ? -1 : rt;
    }

    private int Max = (int) 1e9;

    private int way2(int[] a) {

        int[] dec = getDec(a);
        int n = dec.length;
        if (a[0] > a[n - 1]) {
            return Max;
        }
        for (int i = 0; i < n; ++i) {
            if (dec[i] == i + 1 && dec[n - 1] == n - (i + 1)) {
                return i + 1 + 1;
            }
        }
        return Max;
    }

    private int way1(int[] a) {

        int n = a.length;
        if (a[0] < a[n - 1]) {
            return Max;
        }
        int[] inc = getInc(a);
        for (int i = 0; i < n; ++i) {
            if (inc[i] == i + 1 && inc[n - 1] == n - (i + 1)) {
                return i + 1;
            }
        }
        return Max;
    }

    private void rev(int[] a) {
        int n = a.length;
        int i = 0;
        int j = n - 1;
        while (i < j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            ++i;
            --j;
        }
    }


    private boolean sorted(int[] a) {
        int n = a.length;
        for (int i = 0; i + 1 < n; ++i) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private int[] getInc(int[] a) {
        int n = a.length;
        int[] inc1 = new int[n];
        inc1[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (a[i] > a[i - 1]) {
                inc1[i] = inc1[i - 1] + 1;
            } else {
                inc1[i] = 1;
            }
        }
        return inc1;
    }

    private int[] getDec(int[] a) {
        int n = a.length;
        int[] dec = new int[n];
        dec[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (a[i] < a[i - 1]) {
                dec[i] = dec[i - 1] + 1;
            } else {
                dec[i] = 1;
            }
        }
        return dec;
    }
}
