import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinNumberOfPrimesToSumToTarget {
    private static int LIMIT = 10000;
    private static boolean[] isprime = new boolean[LIMIT + 1];
    private static List<Integer> primes = new ArrayList<>();

    private static void init() {
        if (primes.size() > 0) {
            return;
        }
        Arrays.fill(isprime, true);
        isprime[1] = false;
        for (int i = 2; i <= LIMIT; ++i) {
            if (!isprime[i]) {
                continue;
            }
            if (i > 2 && i % 2 == 0) {
                isprime[i] = false;
                continue;
            }
            for (int j = 2 * i; j <= LIMIT; j += i) {
                isprime[j] = false;
            }
        }
        for (int i = 2; i <= LIMIT; ++i) {
            if (isprime[i]) {
                primes.add(i);
            }
        }

    }

    private int Max = (int) 1e9;
    private Integer[][] dp;

    private int solve(int i, int n, int m) {
        if(n==0){
            return 0;
        }
        if (i == m) {
            return Max;
        }
        if (dp[i][n] != null) {
            return dp[i][n];
        }

        int way1 = solve(i + 1, n, m);
        int way2 = Max;
        int rem = n;
        if (rem >= primes.get(i)) {
            rem -= primes.get(i);
            way2 = 1 + solve(i, rem, m);
        }
        int res = Math.min(way1, way2);
        dp[i][n] = res;
        return res;
    }


    public int minNumberOfPrimes(int n, int m) {
        init();
        dp = new Integer[m][n+1];
        int rt = solve(0, n, m);
        return rt>=Max? -1: rt;
    }

    public static void main(String[] args) {
        System.out.println(new MinNumberOfPrimesToSumToTarget().minNumberOfPrimes(296, 407));
    }
}
