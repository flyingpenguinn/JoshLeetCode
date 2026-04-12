import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinOperationsTransformToAlternatingPrimes {
    private static int MAX = 1000000;
    private static List<Integer> primeNumbers = new ArrayList<>();
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

    public int minOperations(int[] a) {
        if (primeNumbers.isEmpty()) {
            init();
        }
        int res = 0;
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (i % 2 == 0) {
                if (primes[v]) {
                    continue;
                }
                int nextprime = bsearch(primeNumbers, v);
                int diff = primeNumbers.get(nextprime) - v;
                res += diff;
            } else {
                if (!primes[v]) {
                    continue;
                }
                if (v == 2) {
                    res += 2;
                } else {
                    res += 1;
                }
            }
        }
        return res;
    }

    private int bsearch(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
