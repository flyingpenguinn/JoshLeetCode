public class UglyNumberIII {
    public int nthUglyNumber(int n, int a, int b, int c) {

        long l = 1;
        long u = 2000000000;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long nth = getNth(a, b, c, mid);
            if (nth >= n) {
                u = mid - 1;
            } else if (nth < n) {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    // use a venn diagram in set theory to prove
    private long getNth(int a, int b, int c, long mid) {
        return ((mid / a) + (mid / b) + (mid / c)
                - (mid / lcm(a, b))
                - (mid / lcm(b, c))
                - (mid / lcm(a, c))
                + (mid / lcm(a, lcm(b, c))));
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

}
