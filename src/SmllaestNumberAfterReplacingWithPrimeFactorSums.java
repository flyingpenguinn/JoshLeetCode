import java.util.*;

public class SmllaestNumberAfterReplacingWithPrimeFactorSums {
    private static int MAX = 100000;
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

    public int smallestValue(int n) {
        if (primeNumbers.isEmpty()) {
            init();
        }
        if (primes[n]) {
            return n;
        }
        int cur = 0;
        int oldn = n;
        for (int pi : primeNumbers) {
            if (pi > n) {
                break;
            }
            while (n > 0 && n % pi == 0) {
                n /= pi;
                cur += pi;
            }
        }
        if (cur == oldn) {
            // for example 4
            return cur;
        }
        return smallestValue(cur);
    }

    public static void main(String[] args) {
        System.out.println(new SmllaestNumberAfterReplacingWithPrimeFactorSums().smallestValue(4));
    }
}
