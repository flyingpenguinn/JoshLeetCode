import java.util.Arrays;

public class MaxRunningTimeOfNComputers {
    public long maxRunTime(int n, int[] b) {
        long l = 1;
        long u = (long) 1e15;
        Arrays.sort(b);
        while (l <= u) {
            long mid = l + (u - l) / 2L;
            int met = 0;
            long rem = mid;
            int i = 0;
            int[] cb = Arrays.copyOf(b, b.length);
            while (met < n && i < cb.length) {
                int remb = cb.length - i;
                if (n - met > remb) {
                    // we are not going to make it
                    break;
                }
                if (cb[i] >= rem) {
                    cb[i] -= rem;
                    rem = mid;
                    ++met;
                } else {
                    if (n - met == remb) {
                        break;// can't borrow this one any more
                    }
                    rem -= cb[i];
                    ++i;
                }
            }
            if (met < n) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return u;
    }
}
