public class PrimeSubtractionOperation {
    public boolean primeSubOperation(int[] a) {
        int pre = 0;
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            if (a[i] <= pre) {
                return false;
            }
            boolean found = false;
            for (int j = a[i] - (pre + 1); j >= 2; --j) {
                if (isprime(j)) {
                    pre = a[i] - j;
                    found = true;
                    break;
                }
            }
            if (!found) {
                pre = a[i];
            }
        }
        return true;
    }

    private boolean isprime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
