import java.util.HashSet;
import java.util.Set;

public class MinNumOpsToMakeStringSorted {
    // permutations with repetitions
    // use power mod to do factor division
    private long[] dp;
    private long mod = 1000000007;

    private long factor(int i) {
        if (i <= 1) {
            return 1;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        long rt = (factor(i - 1) * i) % mod;
        dp[i] = rt;
        return rt;
    }

    public int makeStringSorted(String s) {
        int n = s.length();
        dp = new long[n + 1];
        long steps = 0;
        int[] count = new int[26];
        for (int i = n - 1; i >= 0; i--) {
            int cind = s.charAt(i) - 'a';
            count[cind]++;
            long smaller = sumof(count, cind);
            // count of smaller * (n-i-1)!/product of repetitions !
            long cur = smaller * factor(n - i - 1);
            cur %= mod;
            for (int j = 0; j < 26; j++) {
                long pow = pow(factor(count[j]), mod - 2, mod);
                cur *= pow;
                cur %= mod;
            }

            steps += cur;
            steps %= mod;
        }
        return (int) steps;
    }

    private long sumof(int[] c, int ind) {
        long res = 0;
        for (int j = 0; j < ind; j++) {
            res += c[j];
        }
        return res;
    }

    private long pow(long x, long y, long mod) {
        if (y == 0) {
            return 1;
        }
        long half = pow(x, y / 2, mod);
        long res = half * half;
        res %= mod;
        if (y % 2 == 1) {
            res *= x;
        }
        res %= mod;
        return res;
    }
}

class MinOpsToMakeStringSortedSlow {
    // just for the sake of debugging
    public int makeStringSortedslow(String s) {
        char[] sc = s.toCharArray();
        int n = s.length();

        int steps = 0;
        while (true) {
            System.out.println(new String(sc));
            int ri = -1;
            int rj = -1;
            for (int i = 1; i < n; i++) {
                if (sc[i] < sc[i - 1]) {
                    ri = i;
                    for (int j = i; j < n; j++) {
                        if (sc[j] < sc[i - 1]) {
                            rj = j;
                        }
                    }
                }
            }
            if (ri == -1) {
                return steps;
            }
            steps++;
            swap(sc, ri - 1, rj);
            reverse(sc, ri);
        }
    }


    private void swap(char[] sc, int i, int j) {
        char tmp = sc[i];
        sc[i] = sc[j];
        sc[j] = tmp;
    }

    private void reverse(char[] sc, int i) {
        int j = sc.length - 1;
        while (i < j) {
            swap(sc, i++, j--);
        }
    }
}
