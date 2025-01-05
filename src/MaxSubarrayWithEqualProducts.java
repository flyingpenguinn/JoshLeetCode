public class MaxSubarrayWithEqualProducts {
    public int maxLength(int[] a) {
        int n = a.length;
        int res = 1;
        for (int i = 0; i < n; ++i) {
            int p = a[i];
            int g = a[i];
            int l = a[i];

            for (int j = i + 1; j < n; ++j) {
                p *= a[j];
                g = gcd(g, a[j]);
                l = lcm(l, a[j]);
                //System.out.println("p="+p+" g="+g+" l="+l);
                if (p == g * l) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
