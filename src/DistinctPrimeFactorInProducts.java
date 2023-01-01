import java.util.*;

public class DistinctPrimeFactorInProducts {
    private static int MAX = 1000;
    private static boolean[] primes = new boolean[MAX + 1];
    private static List<Integer> primeNumbers = new ArrayList<>();

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

    public int distinctPrimeFactors(int[] a) {
        if (primeNumbers.isEmpty()) {
            init();
        }
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            for (int p : primeNumbers) {
                if (ai % p == 0) {
                    set.add(p);
                }
            }
        }
        return set.size();
    }
}
