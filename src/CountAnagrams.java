public class CountAnagrams {
    // duvude a huge number then mod is the same is multiply the mod inverse of it
    private long Mod = (long) 1e9 + 7;
    private long[] factor;

    public int countAnagrams(String s) {
        int sn = s.length();
        factor = new long[sn + 1];
        initFact(sn);
        String[] ss = s.split(" ");
        long res = 1L;
        for (String cs : ss) {
            long perm = getperm(cs);
            res *= perm;
            res %= Mod;
        }
        return (int) res;
    }


    private void initFact(int n) {
        long last = 1L;
        factor[0] = last;
        for (int i = 1; i <= n; i++) {
            long cur = last * i;
            cur %= Mod;
            factor[i] = cur;
            last = cur;
        }
    }

    private long getperm(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            ++count[c - 'a'];
        }
        int n = s.length();
        long fact = factor[n];
        for (int i = 0; i < 26; ++i) {
            if (count[i] > 1) {
                long factc = factor[count[i]];
                long modin = modInverse(factc, Mod);
                fact *= modin;
                fact %= Mod;
            }
        }
        return fact;
    }

    // copied mod inverse code
    private long modInverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;
        if (m == 1) {
            return 0;
        }
        while (a > 1) {
            // q is quotient
            long q = a / m;
            long t = m;
            // m is remainder now, process
            // same as Euclid's algo
            m = a % m;
            a = t;
            t = y;
            // Update x and y
            y = x - q * y;
            x = t;
        }
        // Make x positive
        if (x < 0) {
            x += m0;
        }
        return x;
    }
}
