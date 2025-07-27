import java.util.*;

public class MinJumpToReachEndViaPrimeTeleportation {
    // quick factorization!
    static boolean[] isprime = null;
    static Set<Integer> primeset = new HashSet<>();
    static List<Integer> primelist = new ArrayList<>();
    static List<Integer>[] fac;
    int maxNum = (int) 1e6;

    private static void getprimes() {
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

    public int minJumps(int[] a) {
        int n = a.length;
        if (isprime == null) {
            isprime = new boolean[maxNum + 1];
            Arrays.fill(isprime, true);
            isprime[0] = false;
            isprime[1] = false;
            getprimes();
            fac = new ArrayList[maxNum + 1];
            for (int i = 0; i <= maxNum; i++) {
                fac[i] = new ArrayList<>();
            }
            for (int i = 2; i <= maxNum; i++) {
                if (fac[i].isEmpty()) {
                    // i is prime (no smaller prime has marked it yet)
                    for (int j = i; j <= maxNum; j += i) {
                        // mark i as a prime factor of j
                        fac[j].add(i);
                    }
                }
            }
        }
        Map<Integer, Set<Integer>> seen = new HashMap<>();
        Map<Integer, Set<Integer>> seenprimes = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            if (primeset.contains(a[i])) {
                seenprimes.computeIfAbsent(a[i], q -> new HashSet<>()).add(i);
            }
            seen.computeIfAbsent(a[i], q -> new HashSet<>()).add(i);
        }

        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (!primeset.contains(v)) {
                for (int j : fac[v]) {
                    int other = a[i] / j;
                    if (seenprimes.containsKey(j) && primeset.contains(j)) {
                        g.computeIfAbsent(j, q -> new HashSet<>()).add(v);
                    }
                    if (seenprimes.containsKey(other) && primeset.contains(other)) {
                        g.computeIfAbsent(other, q -> new HashSet<>()).add(v);
                    }

                }
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offerLast(new int[]{0, 0});
        boolean[] visited = new boolean[n];
        visited[0] = true;
        Set<Integer> seenv = new HashSet<>();
        while (!q.isEmpty()) {
            int[] top = q.pollFirst();
            int index = top[0];
            int dist = top[1];
            if (index == n - 1) {
                return dist;
            }
            int newdist = dist + 1;
            if (index + 1 < n && !visited[index + 1]) {
                visited[index + 1] = true;
                q.offerLast(new int[]{index + 1, newdist});
            }
            if (index - 1 >= 0 && !visited[index - 1]) {
                visited[index - 1] = true;
                q.offerLast(new int[]{index - 1, newdist});
            }
            if (primeset.contains(a[index]) && !seenv.contains(a[index])) {
                seenv.add(a[index]);
                for (int ne : seenprimes.getOrDefault(a[index], new HashSet<>())) {
                    if (!visited[ne]) {
                        visited[ne] = true;
                        q.offerLast(new int[]{ne, newdist});
                    }
                }
                for (int nv : g.getOrDefault(a[index], new HashSet<>())) {
                    for (int ne : seen.getOrDefault(nv, new HashSet<>())) {
                        if (!visited[ne]) {
                            visited[ne] = true;
                            q.offerLast(new int[]{ne, newdist});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
