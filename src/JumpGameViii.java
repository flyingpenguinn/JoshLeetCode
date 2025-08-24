import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class JumpGameViii {
    private int[] next1;
    private int[] next2;
    private long[] dp;
    private long Max = (long) 2e18;

    public long minCost(int[] a, int[] c) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.length;
        dp = new long[n];
        next1 = new int[n];
        next2 = new int[n];
        Arrays.fill(next1, -1);
        Arrays.fill(next2, -1);
        Arrays.fill(dp, -1L);
        for (int i = n - 1; i >= 0; --i) {
            while (!st.isEmpty() && a[st.peek()] < a[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                next1[i] = st.peek();
            }
            st.push(i);
        }

        st.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!st.isEmpty() && a[st.peek()] >= a[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                next2[i] = st.peek();
            }
            st.push(i);
        }

        return solve(0, c);
    }

    private long solve(int i, int[] c) {
        //  System.out.println(i);
        int n = c.length;
        if (i == n - 1) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        long way1 = next1[i] == -1 ? Max : c[next1[i]] + solve(next1[i], c);
        long way2 = next2[i] == -1 ? Max : c[next2[i]] + solve(next2[i], c);
        dp[i] = Math.min(way1, way2);
        return dp[i];
    }
}
