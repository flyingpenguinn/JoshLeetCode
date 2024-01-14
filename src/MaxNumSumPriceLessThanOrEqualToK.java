public class MaxNumSumPriceLessThanOrEqualToK {
    public long findMaximumNumber(long k, int x) {
        long l = 1;
        long u = (long) 1e15;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (sumBigger(mid, x, k)) {
                u = mid - 1;

            } else {
                l = mid + 1;
            }
        }
        return u;
    }

    private boolean sumBigger(long u, int x, long k) {
        long res = 0;
        for (int bit = 1; bit <= 64; ++bit) {
            if (bit % x == 0) {
                long first = (1 << (bit - 1));
                if (u < first) {
                    break;
                }
                long chunk = 1 << bit;
                long fullchunks = (u - first + 1) / chunk;
                long setsinchunk = chunk / 2;
                long rem = u - first + 1 - fullchunks * chunk;
                long remadd = Math.min(rem, setsinchunk);
                long r1 = fullchunks * setsinchunk;
                long r2 = remadd;
                res += r1;
                res += r2;
                if (res > k) {
                    return true;
                }
            }
        }
        return false;
    }
}
