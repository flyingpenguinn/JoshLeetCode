import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountNumberOfIdealArray {
    // count strictly increasing seq. then use stars and bars argument to "distribute" all n values onto len parts, where len is at most 14
    private List<Integer>[] m;
    private long mod = (long) (1e9 + 7);

    void factor(int lim) {
        for (int i = 1; i <= lim; ++i) {
            for (int j = 2 * i; j <= lim; j += i) {
                m[j].add(i);
            }
        }
    }

    public int idealArrays(int n, int lim) {
        fact = new Long[n + 1];
        m = new ArrayList[lim+1];
        for (int i = 1; i <= lim; ++i) {
            m[i] = new ArrayList<>();
        }
        factor(lim);
        int maxIncreasing = 14;
        long[][] dp = new long[maxIncreasing + 1][lim + 1];

        for (int j = 1; j <= lim; ++j) {
            dp[1][j] = 1;
        }
        for (int i = 2; i <= maxIncreasing; ++i) {
            for (int j = 1; j <= lim; ++j) {
                for (int d : m[j]) {
                    dp[i][j] += dp[i - 1][d] ;
                }
            }
        }
        long[] lens = new long[maxIncreasing + 1];
        for (int i = 1; i <= maxIncreasing; ++i) {
            for (int j = 1; j <= lim; ++j) {
                lens[i] += dp[i][j];
            }
        }
        long res = 0;
        for (int i = 1; i <= maxIncreasing; ++i) {
            long cnk = combi(n - 1, i - 1);
            long cur = cnk * lens[i];
            cur %= mod;
            res += cur;
            res %= mod;
        }
        return (int) res;

    }

    private Long[] fact;


    private long fact(int n) {
        if (fact[n] != null) {
            return fact[n];
        }
        long res = 0;
        if (n == 0) {
            res = 1L;
        } else {
            res = fact(n - 1) * n;
            res %= mod;
        }
        fact[n] = res;
        return res;
    }

    private long combi(int n, int k) {
        if (n < k) {
            return 0;
        }
        if (n == k) {
            return 1;
        }
        long res = fact(n);
        res *= modinverse(fact(k), mod);
        res %= mod;
        res *= modinverse(fact(n - k), mod);
        res %= mod;
        return res;
    }


    private long modinverse(long a, long m) {
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

    public static void main(String[] args) {
        System.out.println(new CountNumberOfIdealArray().idealArrays(2, 5));
        System.out.println(new CountNumberOfIdealArray().idealArrays(5,3));
    }
}
