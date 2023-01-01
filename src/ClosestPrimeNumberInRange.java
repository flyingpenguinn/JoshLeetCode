import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestPrimeNumberInRange {

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


    public int[] closestPrimes(int left, int right) {
        if (primeNumbers.isEmpty()) {
            init();
        }
        int pn = primeNumbers.size();
        int mindiff = MAX;
        int[] minpair = new int[2];
        Arrays.fill(minpair, -1);
        for (int i = 0; i + 1 < pn; ++i) {
            int v1 = primeNumbers.get(i);
            int v2 = primeNumbers.get(i + 1);
            if (v1 < left) {
                continue;
            }
            if (v2 > right) {
                break;
            }
            if (v2 - v1 < mindiff) {
                mindiff = v2 - v1;
                minpair = new int[]{v1, v2};
            } else if (v2 - v1 == mindiff) {
                if (v1 < minpair[0]) {
                    minpair = new int[]{v1, v2};
                }
            }
        }
        return minpair;
    }
}
