import java.util.*;

public class PrimeSubsetSelectionII {
    class Prime {
        boolean[] isprime = null;
        Set<Integer> primeset = new HashSet<>();
        List<Integer> primelist = new ArrayList<>();

        private void getprimes() {
            Arrays.fill(isprime, true);
            isprime[0] = false;
            isprime[1] = false;
            int lim = isprime.length;
            for (long i = 2; i < lim; i++) {
                if (!isprime[(int) i]) {
                    continue;
                }
                for (long j = i * i; j < lim; j += i) {
                    isprime[(int) j] = false;
                }
            }
            for (int i = 0; i < isprime.length; i++) {
                if (isprime[i]) {
                    primelist.add(i);
                    primeset.add(i);
                }
            }
        }

        private final Map<Integer, Set<Integer>> g = new HashMap<>();

        // for num <= 1e5, at most 6!
        private List<Integer> distinctPrimeFactors(int x) {
            List<Integer> res = new ArrayList<>();
            int t = x;

            for (int i = 0; i < primelist.size(); ++i) {
                int p = primelist.get(i);
                if ((long) p * p > t) {
                    break;
                }
                if (t % p == 0) {
                    res.add(p);
                    while (t % p == 0) {
                        t /= p;
                    }
                }
            }

            if (t > 1) {
                res.add(t);
            }

            return res;
        }


        public void init(int maxNum) {

            if (isprime == null) {
                isprime = new boolean[maxNum + 1];
                getprimes();
            }
        }
    }

    public List<Integer> maxPrimeSubset(int n, int s) {
        Prime p = new Prime();
        p.init(n);
        List<Integer> list = p.primelist;
        int[] pa = new int[list.size()];
        for (int i = 0; i < pa.length; ++i) {
            pa[i] = list.get(i);
        }
        int maxsum = solve1(pa, s);
        int minpick = solve2(pa, maxsum);
        List<Integer> res = new ArrayList<>();
        solve3(pa, choice2, 0, maxsum, res);
        return res;
    }

    private void solve3(int[] pa, int[][] ch, int i, int sum, List<Integer> res) {
        int n = pa.length;
        if (i == n) {
            return;
        }
        if (ch[i][sum] == 1) {
            solve3(pa, ch, i + 1, sum, res);
        } else {
            res.add(pa[i]);
            solve3(pa, ch, i + 1, sum - pa[i], res);
        }
    }

    private int solve2(int[] pa, int t) {
        int n = pa.length;
        dp2 = new int[n][t + 1];
        for(int i=0; i<n; ++i){
            Arrays.fill(dp2[i], -1);
        }
        choice2 = new int[n][t + 1];
        return dodp2(pa, 0, t);
    }

    private int dodp2(int[] pa, int i, int rem) {
        int n = pa.length;
        if (rem < 0) {
            return Max;
        }
        if (i == n) {
            return rem == 0 ? 0 : Max;
        }
        if (dp2[i][rem] != -1) {
            return dp2[i][rem];
        }
        int way1 = dodp2(pa, i + 1, rem);
        int way2 = 1 + dodp2(pa, i + 1, rem - pa[i]);
        int res = 0;
        if (way1 >= way2) {
            choice2[i][rem] = 2;
            res = way2;
        } else {
            choice2[i][rem] = 1;
            res = way1;
        }
        dp2[i][rem] = res;
        return res;
    }

    private int Max = (int) 1e9;
    private int[][] dp1;
    private int[][] dp2;
    private int[][] choice2;

    private int solve1(int[] pa, int limit) {
        int n = pa.length;
        dp1 = new int[n][limit + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp1[i], -1);
        }
        return dodp(pa, 0, 0, limit);
    }

    private int Min = (int) -1e9;

    private int dodp(int[] pa, int i, int sum, int limit) {
        int n = pa.length;
        if (sum > limit) {
            return Min;
        }
        if (i == n) {
            return sum;
        }
        if (dp1[i][sum] != -1) {
            return dp1[i][sum];
        }
        int way1 = dodp(pa, i + 1, sum, limit);
        int way2 = dodp(pa, i + 1, sum + pa[i], limit);

        int res = Math.max(way1, way2);
        dp1[i][sum] = res;
        return res;
    }
}
