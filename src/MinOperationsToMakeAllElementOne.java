public class MinOperationsToMakeAllElementOne {
    private int MAX = (int) 1e9;

    public int minOperations(int[] a) {
        int n = a.length;
        int cur = a[0];
        int num1 = 0;
        for (int ai : a) {
            if (ai == 1) {
                ++num1;
            }
        }
        if (num1 > 0) {
            return n - num1;
        }
        for (int i = 1; i < n; ++i) {
            cur = gcd(cur, a[i]);
        }
        if (cur != 1) {
            return -1;
        }
        int res = MAX;
        for (int i = 0; i < n; ++i) {
            int g = a[i];
            for (int j = i + 1; j < n; ++j) {
                g = gcd(g, a[j]);
                if (g == 1) {
                    res = Math.min(res, j - i);

                    break;
                }
            }
        }
        return n + res - 1;
    }


    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
