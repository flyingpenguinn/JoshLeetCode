import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSubarrayMinProduct {
    // a twist on largest histogram...
    public int maxSumMinProduct(int[] a) {

        int n = a.length;
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        Deque<int[]> st = new ArrayDeque<>();
        // index, height
        long res = 0;
        for (int i = 0; i <= n; i++) {
            int ch = i == n ? 0 : a[i];
            int lasti = i;
            while (!st.isEmpty() && st.peek()[1] >= ch) {
                lasti = st.peek()[0];
                int lasth = st.peek()[1];
                long cur = 1L * lasth * diff(sum, i, lasti);
                // from lasti as starting point to i-1 all >=lasth, can for a rect
                res = Math.max(res, cur);
                st.pop();
            }
            st.push(new int[]{lasti, ch});
        }
        st.pop();
        return (int) (res % 1000000007);
    }

    private long diff(long[] sum, int i, int j) {
        return j == 0 ? sum[i - 1] : (sum[i - 1] - sum[j - 1]);
    }
}
