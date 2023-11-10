public class DistributeCandiesAmongChildrenIII {
    // result oriented programming...
    public long distributeCandies(int n, int limit) {
        if (n < limit) {
            long start = 1;
            long end = n + 1;
            long res = (start + end) * (end - start + 1) / 2;
            return res;
        } else if (n >= limit && n <= 2 * limit) {
            long peak = limit + 1;
            long start1 = peak - (n - limit);
            long seg1 = peak - start1 + 1;
            long sum1 = (start1 + peak) * seg1 / 2;
            long seg2 = peak - seg1;
            long start2 = peak - seg2;
            long sum2 = (start2 + peak - 1) * seg2 / 2;
            return sum1 + sum2;
        } else if (n <= 3 * limit) {
            long diff = n - 2 * limit;
            long start = 1;
            long end = limit + 1 - diff;
            long res = (start + end) * (end - start + 1) / 2;
            return res;
        } else {
            return 0;
        }
    }

    public long distributeCandiesSlow(int n, int limit) {
        return solve(n, 3, limit);
    }

    private long solve(long n, long rem, long p) {
        if (rem == 2) {
            if (n - p > p) {
                return 0;
            }
            long start = Math.max(n - p, 0);
            long end = Math.min(p, n);
            long res = end - start + 1;
            return res;
        }
        long res = 0;
        for (long i = 0; i <= p && n - i >= 0; ++i) {
            long cur = solve(n - i, rem - 1, p);
            System.out.println((n - i) + " " + cur);
            res += cur;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new DistributeCandiesAmongChildrenIII().distributeCandies(18, 6));
        System.out.println(new DistributeCandiesAmongChildrenIII().distributeCandiesSlow(19, 6));
    }
}
