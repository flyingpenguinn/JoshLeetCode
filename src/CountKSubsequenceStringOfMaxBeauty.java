import java.util.Arrays;
import java.util.Collections;

public class CountKSubsequenceStringOfMaxBeauty {
    private long Mod = (long) (1e9 + 7);

    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int n = s.length();
        fact = new Long[n + 1];
        Integer[] count = new Integer[26];
        Arrays.fill(count, 0);
        for (char c : s.toCharArray()) {
            int cind = c - 'a';
            ++count[cind];
        }
        int dist = 0;
        for (int i = 0; i < 26; ++i) {
            if (count[i] > 0) {
                ++dist;
            }
        }
        if (dist < k) {
            return 0;
        }

        Arrays.sort(count, Collections.reverseOrder());
        int i = 0;
        int ck = k;
        long res = 1;
        while (i < count.length && ck > 0) {
            int j = i;
            long multi = 1;
            while (j < count.length && count[j].equals(count[i])) {
                multi *= count[j];
                multi %= Mod;
                ++j;
            }
            int nums = j - i;
            if (nums <= ck) {
                res *= multi;
                res %= Mod;
                ck -= nums;
            } else {
                long calc = mypow(count[i], ck) * combi(nums, ck);
                res *= calc;
                res %= Mod;
                ck = 0;
            }
            i = j;
        }
        return (int) res;
    }

    private long mypow(int a, int b) {
        if(b==0){
            return 1;
        }
        long half = mypow(a, b/2);
        long res = half*half;
        res %= Mod;
        if(b%2==1){
            res *= a;
            res %= Mod;
        }
        return res;
    }

    private long fact(int n) {
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

    private Long[] fact;

    // n! /( k!*(n-k)!)
    // a/b mod n = a mod n * modinverse(b) mod n
    private long combi(long n, long k) {
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

}
