import base.ArrayUtils;

import java.util.*;

public class CountValidPathsInTree {
    private Map<Integer, Set<Integer>> t = new HashMap<>();
    private long res = 0;
    private int maxn = 10050;

    public long countPaths(int n, int[][] edges) {
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
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t.computeIfAbsent(v1, k -> new HashSet<>()).add(v2);
            t.computeIfAbsent(v2, k -> new HashSet<>()).add(v1);
        }
        dfs(1, -1);
        return res;
    }

    // num of nodes that can reach it through no prime
    // num of nodes that can reach it through exactly one prime
    private long[] dfs(int i, int p) {
        long nonprime = 0;
        long primes = 0;
        List<long[]> vs = new ArrayList<>();
        boolean curprime = prime[i];
        for (int ne : t.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            long[] cur = dfs(ne, i);
            if (curprime) {
                long v1 = cur[0];
                long v2 = nonprime;
                res += v1 * v2;
                res += cur[0];
            } else {
                long v1 = cur[0];
                long v2 = primes;
                res += cur[1];
                res += v1 * v2;
                v1 = cur[1];
                v2 = nonprime;
                res += v1 * v2;
            }
            nonprime += cur[0];
            primes += cur[1];
        }

        if (curprime) {
            return new long[]{0, nonprime + 1};
        } else {
            return new long[]{nonprime + 1, primes};
        }
    }

    private static boolean[] prime;

    public static void main(String[] args) {
        System.out.println(new CountValidPathsInTree().countPaths(4, ArrayUtils.read("[[1,2],[4,1],[3,4]]")));
    }
}
