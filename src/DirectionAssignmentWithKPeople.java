public class DirectionAssignmentWithKPeople {
    private static long Mod = (long) (1e9 + 7);

    public int countVisiblePeople(int n, int pos, int k) {

        long res = 0;
        for (int left = 0; left <= k; ++left) {
            int right = k - left;
            if (left > pos || right > (n - 1 - pos)) {
                continue;
            }
            long lr = combi(pos, left);
            long rr = combi(n - 1 - pos, right);
            long cr = lr * rr;
            cr %= Mod;
            cr *= 2;
            cr %= Mod;
            res += cr;
            res %= Mod;
        }
        return (int) res;
    }

    private static Long[] fact = new Long[100005];

    private static long fact(int n) {
        if (fact[n] != null) {
            return fact[n];
        }
        long res = 0;
        if (n == 0) {
            res = 1L;
        } else {
            res = fact(n - 1) * n;
            res %= Mod;
        }
        fact[n] = res;
        return res;
    }

    // n! /( k!*(n-k)!)
    // a/b mod n = a mod n * modinverse(b) mod n
    private static long combi(long n, long k) {
        if (n < k) {
            return 0;
        }
        if (n == k) {
            return 1;
        }
        long res = fact((int) n);
        res *= modinverse(fact((int) k), Mod);
        res %= Mod;
        res *= modinverse(fact((int) (n - k)), Mod);
        res %= Mod;
        return res;
    }


    private static long modinverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;
        if (m == 1) {
            return 0;
        }
        while (a > 1) {
            long q = a / m;
            long t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0) {
            x += m0;
        }
        return x;
    }
}
