import base.ArrayUtils;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class MaxNumberOfBooksYouCanTake {
    // find the last index j where a[j]-j<=a[i]-i
    // we then use the dp[j] value to deduce solution for i

    public long maximumBooks(int[] a) {
        int n = a.length;
        Stack<int[]> st = new Stack<>();

        long res = 0;
        long[] dp = new long[n];

        for (int i = 0; i < n; ++i) {
            long v = a[i];
            int prev = -1;

            while (!st.empty() && st.peek()[0] > a[i] - i) {
                st.pop();
            }

            if (!st.empty()) {
                prev = st.peek()[1];
            }

            st.push(new int[]{a[i] - i, i});
            long cur = 0;
            if (prev == -1) {
                long len = Math.min(i + 1, a[i]);
                cur = (v - len + 1 + v) * len / 2;
            } else {
                long accu = dp[prev];
                int len = i - prev;
                cur = (v - len + 1 + v) * len / 2;

                if (len <= a[i] - a[prev]) {
                    cur += accu;
                }
            }

            dp[i] = Math.max(cur, 0L + a[i]);
            res = Math.max(res, cur);
        }

        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MaxNumberOfBooksYouCanTake().maximumBooks(ArrayUtils.read1d("[8,5,2,7,9]")));
    }
}
