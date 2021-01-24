import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountWaysToMakeArrayWithProduct {
    // for each repeated factor, it's a composition on k boxes
    // we then MULTIPLY them together
    private int maxn = 10050;
    private static boolean[] prime;
    private static long[][] combi;
    private static int[] fact;

    public int[] waysToFillArray(int[][] qs) {
        int n = qs.length;
        int[] res = new int[n];
        if (combi == null) {
            combi = new long[maxn + 1][41];
            for (int i = 0; i <= maxn; i++) {
                combi[i][0] = 1;
            }
            //combi(0,i)==0
            for (int i = 1; i <= maxn; i++) {
                for (int j = 1; j <= 40; j++) {
                    combi[i][j] = (combi[i - 1][j] + combi[i - 1][j - 1]) % mod;
                }
            }
        }
        if (fact == null) {
            fact = new int[maxn + 1];
            fact[0] = 1;
            long cur = 1;
            for (int i = 1; i <= maxn; i++) {
                cur *= i;
                fact[i] = (int) (cur % mod);
            }
        }
        if (prime == null) {
            prime = new boolean[maxn + 1];
            Arrays.fill(prime, true);
            prime[0] = prime[1] = false;
            for (int i = 2; i <= maxn; i++) {
                if (!prime[i]) {
                    continue;
                }
                for (int j = 2; j * i <= maxn; j++) {
                    prime[j * i] = false;
                }
            }
        }
//       System.out.println(combi[6][4]+" "+combi[5][3]);
        for (int i = 0; i < n; i++) {
            int num = qs[i][1];
            int k = qs[i][0];
            List<Integer> fact = new ArrayList<>();
            for (int j = 2; j <= num / 2; j++) {
                while (prime[j] && num % j == 0) {
                    fact.add(j);
                    num /= j;
                }
            }
            if (prime[num]) {
                fact.add(num);
            }
            //     System.out.println(fact);
            res[i] = find(n, k, fact);
        }

        return res;
    }

    private int mod = 1000000007;

    private int find(int n, int k, List<Integer> fact) {
        int m = fact.size();
        long res = 1; // purely 1 and k, k approaches
        int i = 0;
        while (i < fact.size()) {
            int j = i;
            while (j < fact.size() && fact.get(j) == fact.get(i)) {
                j++;
            }
            int balls = j - i;
            int boxes = k;
            long cur = comp(balls, boxes);
            res *= cur;
            res %= mod;
            i = j;
        }
        return (int) res;
    }

    // c(n+k-1, n) == c(n+k-1, k-1)
    private long comp(int balls, int boxes) {

        long res = combi[balls + boxes - 1][balls];
        return res;
    }
}
