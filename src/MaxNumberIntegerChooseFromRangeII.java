import java.util.Arrays;
import java.util.TreeMap;

public class MaxNumberIntegerChooseFromRangeII {
    public int maxCount(int[] banned, int n, long maxSum) {
        Arrays.sort(banned);
        TreeMap<Long, Long> tm = new TreeMap<>();
        long accu = 0;
        for (int b : banned) {
            accu += b;
            tm.put(Long.valueOf(b), accu);
        }
        long l = 1;
        long u = n;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long msum = (1 + mid) * mid / 2;
            Long deduct = tm.floorKey(mid);
            long dv = deduct == null ? 0 : tm.get(deduct);
            msum -= dv;
            if (msum <= maxSum) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        long ou = u;
        for (long b : tm.keySet()) {
            if (b <= ou) {
                --u;
            }
        }
        return (int) u;
    }
}
