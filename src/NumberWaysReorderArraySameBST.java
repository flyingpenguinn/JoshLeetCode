import java.util.ArrayList;
import java.util.List;

public class NumberWaysReorderArraySameBST {
    private static long mod = (long) (1e9 + 7);
   
    private static long[] fact;
    private static long[] invFact;

    // call getFacts to init
    private static void getFacts(int n) {
        if (fact == null) {
            fact = new long[n];
            invFact = new long[n];
        } else {
            return;
        }
        fact[0] = 1;
        for (int i = 1; i < n; ++i) {
            fact[i] = fact[i - 1] * i % mod;
        }

        invFact[n - 1] = pow(fact[n - 1], mod - 2);
        for (int i = n - 1; i >= 1; --i) {
            invFact[i - 1] = invFact[i] * i % mod;
        }

    }


    private static long comb(int n, int r) {
        if (r < 0 || r > n) {
            return 0;
        }

        return fact[n] * invFact[r] % mod * invFact[n - r] % mod;
    }

    private static long pow(long a, long b) {
        long res = 1;

        while (b > 0) {
            if ((b & 1) != 0) {
                res = res * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return res;
    }

    public int numOfWays(int[] a) {
        int n = a.length;
        getFacts(1020);
       List<Integer> l = new ArrayList<>();
       for(int ai: a){
           l.add(ai);
       }
        long rt = solve(l) - 1;
        rt = (rt % mod + mod) % mod;
        return (int) rt;
    }

    private long solve(List<Integer> a) {
        if (a.size()<=1) {
            return 1;
        }
        int root = a.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for(int i=1; i<a.size(); ++i){
            if(a.get(i)<root){
                left.add(a.get(i));
            }else{
                right.add(a.get(i));
            }
        }
        long lr = solve(left);
        long rr = solve(right);
        int llen = left.size();
        int rlen = right.size();
        long ways = comb(llen + rlen, rlen);
        long res = lr;
        res *= rr;
        res %= mod;
        res *= ways;
        res %= mod;

        return res;
    }
}
