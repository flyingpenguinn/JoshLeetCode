import java.util.*;

public class CountPrimeGapBalancedSubarrays {
    static boolean[] isprime = null;
    static Set<Integer> primeset = new HashSet<>();
    static List<Integer> primelist = new ArrayList<>();

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int primeSubarray(int[] a, int k) {
        if (isprime == null) {
            isprime = new boolean[1000001];
            Arrays.fill(isprime, true);
            isprime[0] = false;
            isprime[1] = false;
            getprimes();
        }
        int n = a.length;
        int j = 0;
        TreeMap<Integer, Integer> primes = new TreeMap<>();
        Map<Integer, Integer> pm = new HashMap<>();
        int pcount = 0;
        int res = 0;
        int allpcount = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (primeset.contains(v)) {
                update(primes, v, 1);
                ++pcount;
                ++allpcount;
            }
            pm.putIfAbsent(allpcount, i);
            while (!primes.isEmpty() && primes.lastKey() - primes.firstKey() > k) {
                int vj = a[j];
                if (primeset.contains(vj)) {
                    update(primes, vj, -1);
                    --pcount;
                }
                ++j;
            }
            if (pcount >= 2 && (!primes.isEmpty() && primes.lastKey() - primes.firstKey() <= k)) {
                int maxj = pm.get(allpcount - 1);
                // j... maxj
                int count = maxj - j + 1;
                res += count;
            }
        }
        return res;
    }

    private void getprimes() {
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
}
