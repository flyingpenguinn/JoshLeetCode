public class SplitArrayIntoPrimeIndices {
    public long splitArray(int[] a) {
        int n = a.length;
        long suma = 0;
        long sumb = 0;
        for (int i = 0; i < n; ++i) {
            if (isprime(i)) {
                suma += a[i];
            } else {
                sumb += a[i];
            }
        }
        return Math.abs(suma - sumb);
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
