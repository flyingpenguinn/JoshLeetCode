public class MaxValueInAGivenIndex {
    // try to put a good number in that index then calc the sum to see if we exceed
    public int maxValue(int n, int index, int maxsum) {
        long l = 1;
        long u = 1000000000;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long left = count(mid, index);
            long right = count(mid, n - 1 - index);
            if (left + right + mid <= maxsum) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return (int) u;
    }

    // from 1 to x, then all 1s for the remaining positions
    private long count(long x, long n) {
        if (x - n >= 1) {
            long head = x - n;
            long sum = (head + x - 1) * n / 2;
            return sum;
        } else {
            long con = x - 1;
            long sum1 = (1 + x - 1) * con / 2;
            long sum2 = n - con;
            return sum1 + sum2;
        }
    }
}
