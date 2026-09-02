import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaxNumberOfBooksYouCanTake {
    // find the last index j where a[j]-j<=a[i]-i
    // we then use the dp[j] value to deduce solution for i

    public long maximumBooks(int[] a) {
        int n = a.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] left = new int[n];
        Arrays.fill(left, -1);
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && (a[st.peek()] - st.peek() > a[i] - i)) {
                st.pop();
            }
            if (!st.isEmpty()) {
                left[i] = st.peek();
            }
            st.push(i);
        }
        long res = 0;
        long[] dp = new long[n];
        for (int i = 0; i < n; ++i) {
            int prev = left[i];
            long v = a[i];
            long len = 0;
            if (prev == -1) {
                len = Math.min(i + 1, v);
            } else {
                len = i - prev;
            }
            long start = v - len + 1;
            long csum = (start + v) * len / 2;
            if (prev != -1) {
                csum += dp[prev];
            }
            res = Math.max(res, csum);
            dp[i] = csum;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MaxNumberOfBooksYouCanTake().maximumBooks(ArrayUtils.read1d("[8,5,2,7,9]")));
    }
}
