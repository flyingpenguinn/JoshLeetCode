public class NumberOfSubarraysGcdEqualK {
    public int subarrayGCD(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int gcd = a[i];
            for (int j = i; j < n; ++j) {
                gcd = gcd(gcd, a[j]);
                if (gcd == k) {
                    // System.out.println(i+" "+j+" "+ngcd);
                    ++res;
                } else if (gcd < k) {
                    break;
                }

            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
