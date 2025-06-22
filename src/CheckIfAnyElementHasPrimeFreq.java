public class CheckIfAnyElementHasPrimeFreq {
    public boolean checkPrimeFrequency(int[] a) {
        int n = a.length;
        int[] cnt = new int[200];
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            ++cnt[v];
        }
        for (int i = 0; i < 200; ++i) {
            int occ = cnt[i];
            if (occ == 0 || occ == 1) {
                continue;
            }
            if (isprime(occ)) {
                return true;
            }
        }
        return false;
    }

    private boolean isprime(int n) {
        if (n == 1 || n == 0) {
            return false;
        }
        for (int i = 2; i < n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
