public class NumberOfSubarraysLcmEqualK {
    // if lcm too big just break
    public int subarrayLCM(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            long lcm = a[i];
            for (int j = i; j < n && lcm <= k; ++j) {
                long nlcm = lcm(lcm, a[j]);
                //  System.out.println(nlcm+" "+i+"->"+j);
                if (nlcm == k) {
                    ++res;
                }
                lcm = nlcm;
            }
        }
        return res;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        //  System.out.println(a+" "+b);
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
