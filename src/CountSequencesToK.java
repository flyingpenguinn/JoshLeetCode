import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CountSequencesToK {
    private static class Fraction {
        private long a;
        private long b;


        private long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public Fraction(long a, long b) {
            this.a = a;
            this.b = b;
            long g = gcd(a, b);
            this.a /= g;
            this.b /= g;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Fraction fraction)) return false;
            return a == fraction.a && b == fraction.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        @Override
        public String toString() {
            return a +
                    "/" + b;
        }
    }

    public int countSequences(int[] a, long k) {
        int n = a.length;
        dp = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = new HashMap<>();
        }
        return solve(a, 0, new Fraction(1, 1), k);
    }

    private Map<Fraction, Integer>[] dp;

    private int solve(int[] a, int i, Fraction f, long k) {
        int n = a.length;
        if (i == n) {
            if (f.a == k && f.b == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[i].containsKey(f)) {
            return dp[i].get(f);
        }
        Fraction nf1 = new Fraction(f.a * a[i], f.b);
        Fraction nf2 = new Fraction(f.a, f.b * a[i]);
        int way1 = solve(a, i + 1, nf1, k);
        int way2 = solve(a, i + 1, nf2, k);
        int way3 = solve(a, i + 1, f, k);
        int res = way1 + way2 + way3;
        dp[i].put(f, res);
        return res;
    }
}
