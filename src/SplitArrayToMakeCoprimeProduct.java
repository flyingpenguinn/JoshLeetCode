import java.util.*;

public class SplitArrayToMakeCoprimeProduct {
    // prime solution for copy
    private static int MAX = 1000000;
    private static boolean[] primes = new boolean[MAX + 1];

    private static List<Integer> primeNumbers = new ArrayList<>();


    // get all primes
    private static void init() {
        if (!primeNumbers.isEmpty()) {
            // already inited
            return;
        }
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


    private Map<Integer, Integer> rm = new HashMap<>();

    public int findValidSplit(int[] a) {
        init();
        int n = a.length;
        Map<Integer, Integer>[] dm = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            dm[i] = new HashMap<>();
            long limit = (long) Math.sqrt(a[i]);
            int cnum = a[i];
            for (int j = 0; j < primeNumbers.size() && primeNumbers.get(j) <= cnum && primeNumbers.get(j) <= limit; ++j) {
                int count = 0;
                Integer pnum = primeNumbers.get(j);
                while (cnum % pnum == 0) {
                    ++count;
                    cnum /= pnum;
                }
                update(rm, pnum, count);
                update(dm[i], pnum, count);
                // only check up to sqrt. if there is one left, it's a prime (could be the number itself)
            }
            if (primes[cnum]) {
                update(rm, cnum, 1);
                update(dm[i], cnum, 1);
            }
        }

        Map<Integer, Integer> lm = new HashMap<>();
        for (int i = 0; i < n - 1; ++i) {
            Map<Integer, Integer> cm = dm[i];
            for (int k : cm.keySet()) {
                int cmv = cm.get(k);
                update(rm, k, -cmv);
                update(lm, k, cmv);
            }
            if (!hasCommon(rm, lm)) {
                return i;
            }
        }
        return -1;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private boolean hasCommon(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        boolean found = false;
        for (int k1 : m1.keySet()) {
            if (m2.containsKey(k1)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
