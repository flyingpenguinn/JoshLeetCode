import java.util.*;

public class SumOfPerfectSquareAncestors {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    private static List<Integer> primeNumbers = new ArrayList<>();
    private static int MAX = 100000;
    private static boolean[] primes = new boolean[MAX + 1];

    // get all primes
    private static void init() {
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i <= MAX; ++i) {
            if (i > 2 && i % 2 == 0) {
                primes[i] = false;
            }
            if (!primes[i]) {
                continue;
            }
            for (int j = 2 * i; j <= MAX; j += i) {
                primes[j] = false;
            }
        }
        for (int i = 0; i < primes.length; ++i) {
            if (primes[i]) {
                primeNumbers.add(i);
            }
        }

    }

    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        if (primeNumbers.isEmpty()) {
            init();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            g.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
            g.computeIfAbsent(v2, k -> new ArrayList<>()).add(v1);
        }
        Map<Set<Integer>, Long> fact = new HashMap<>();
        dfs(g, 0, -1, fact, nums);
        return res;
    }

    private long res = 0;

    private void dfs(Map<Integer, List<Integer>> g, int i, int p, Map<Set<Integer>, Long> fact, int[] nums) {
        int cv = nums[i];
        int sqrt = (int) Math.sqrt(cv);
        Set<Integer> odds = new HashSet<>();
        if (primes[cv]) {
            odds.add(cv);
        } else {
            for (int j = 0; j < primeNumbers.size() && primeNumbers.get(j) <= sqrt; ++j) {
                int cp = primeNumbers.get(j);
                if (cv % cp == 0) {
                    int count = 0;
                    while (cv % cp == 0) {
                        cv /= cp;
                        ++count;
                    }
                    if (count % 2 == 1) {
                        odds.add(cp);
                    }

                    if (primes[cv]) {
                        odds.add(cv);
                        break;
                    }
                }

            }
        }
        long pre = fact.getOrDefault(odds, 0L);
        res += pre;
        fact.put(odds, pre + 1);
        for (int ne : g.getOrDefault(i, new ArrayList<>())) {
            if (ne == p) {
                continue;
            }
            dfs(g, ne, i, fact, nums);
        }
        if (pre == 0) {
            fact.remove(odds);
        } else {
            fact.put(odds, pre);
        }
    }
}
