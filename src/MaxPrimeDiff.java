public class MaxPrimeDiff {
    // 1 is not prime!
    public int maximumPrimeDifference(int[] a) {
        int n = a.length;
        int first = -1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (isprime(a[i])) {
                if (first != -1) {
                    int cur = i - first;
                    res = Math.max(res, cur);
                } else {
                    first = i;
                }
            }
        }
        return res;
    }

    private boolean isprime(int n) {
        if (n == 1) {
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
