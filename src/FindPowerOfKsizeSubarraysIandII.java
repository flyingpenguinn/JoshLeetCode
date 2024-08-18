import java.util.ArrayDeque;
import java.util.Deque;

public class FindPowerOfKsizeSubarraysIandII {
    // don't really need the deque
    public int[] resultsArray(int[] a, int k) {
        int n = a.length;
        Deque<int[]> dq = new ArrayDeque<>();
        int[] res = new int[n - k + 1];
        int ri = 0;
        for (int i = 0; i < n; ++i) {
            int head = i - k;
            if (head >= 0) {
                if (dq.peekFirst()[1] <= head) {
                    dq.pollFirst();
                }
            }
            if (!dq.isEmpty() && dq.peekLast()[0] == a[i] - 1) {
                dq.offerLast(new int[]{a[i], i});
            } else if (!dq.isEmpty()) {
                dq.clear();
                dq.offerLast(new int[]{a[i], i});
            } else {
                dq.offerLast(new int[]{a[i], i});
            }
            if (dq.size() >= k) {
                res[ri++] = a[i];
            } else if (i - k + 1 >= 0) {
                res[ri++] = -1;
            }
        }
        return res;
    }
}
