public class FindMaxFactorScore {
    public long maxScore(int[] a) {
        int n = a.length;
        long gcd = a[0];
        long lcm = a[0];
        for (int i = 1; i < n; ++i) {
            gcd = gcd(gcd, a[i]);
            lcm = lcm(lcm, a[i]);
        }
        // System.out.println("remove nothing gcd is "+gcd + " lcm is "+lcm);
        long res = gcd * lcm;
        for (int i = 0; i < n; ++i) {
            long ngcd = -1;
            long nlcm = -1;
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                if (ngcd == -1) {
                    ngcd = a[j];
                    nlcm = a[j];
                } else {
                    ngcd = gcd(ngcd, a[j]);
                    nlcm = lcm(nlcm, a[j]);
                }
            }
            long cres = ngcd * nlcm;
            res = Math.max(res, cres);
            // System.out.println("remove "+a[i]+" gcd is "+ngcd + " lcm is "+nlcm);
        }
        return res;
    }

    private long gcd(long a, long b) {
        if (b > a) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
