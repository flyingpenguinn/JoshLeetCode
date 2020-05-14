public class PreimageSizeFactorialZero {
    public int preimageSizeFZF(int k) {
        long l = 1;
        // in case the number that corresponds to k is bigger than int
        long u = Long.MAX_VALUE;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long f5 = getf5(mid);
            //System.out.println(mid+" "+f5);
            if (f5 == k) {
                return 5;
            } else if (f5 < k) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return 0;
    }

    long getf5(long m) {
        long c = 0;
        long b = 5L;
        while (m >= b) {
            c += m / b;
            b *= 5;
        }
        return c;
    }
}
