public class PrimePermutations {
    long Mod = 1000000007;

    public int numPrimeArrangements(int n) {
        int primes = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                primes++;
            }
        }
        return (int) ((fact(primes) * fact(n - primes)) % Mod);

    }

    private long fact(int n) {
        return n == 0 ? 1 : n * fact(n - 1) % Mod;
    }

    private boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
