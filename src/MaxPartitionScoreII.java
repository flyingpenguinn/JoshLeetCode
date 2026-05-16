import java.util.ArrayDeque;

public class MaxPartitionScoreII {
    // TODO...
    static class Line {
        long m, b, cnt;

        Line(long m, long b, long cnt) {
            this.m = m;
            this.b = b;
            this.cnt = cnt;
        }

        long value(long x) {
            return m * x + b;
        }
    }

    static class State {
        long cost;
        long cnt;

        State(long cost, long cnt) {
            this.cost = cost;
            this.cnt = cnt;
        }
    }

    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        long[] pre = new long[n + 1];

        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }

        long lo = 0, hi = 1_000_000_000_000_000L;

        while (lo < hi) {
            long mid = (lo + hi + 1) >>> 1;
            State s = solve(pre, mid);

            if (s.cnt >= k) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        State finalState = solve(pre, lo);
        return finalState.cost - lo * k;
    }

    private State solve(long[] pre, long lambda) {
        int n = pre.length - 1;

        long[] dp = new long[n + 1];
        long[] cnt = new long[n + 1];

        ArrayDeque<Line> dq = new ArrayDeque<>();

        dq.addLast(makeLine(pre, dp, cnt, 0));

        for (int i = 1; i <= n; i++) {
            long x = pre[i];

            while (dq.size() >= 2) {
                Line a = dq.pollFirst();
                Line b = dq.peekFirst();

                if (betterOrEqual(b, a, x)) {
                    continue;
                } else {
                    dq.addFirst(a);
                    break;
                }
            }

            Line best = dq.peekFirst();

            dp[i] = best.value(x) + tri(x) + lambda;
            cnt[i] = best.cnt + 1;

            Line nl = makeLine(pre, dp, cnt, i);

            while (dq.size() >= 2) {
                Line l3 = dq.pollLast();
                Line l2 = dq.peekLast();

                if (bad(l2, l3, nl)) {
                    continue;
                } else {
                    dq.addLast(l3);
                    break;
                }
            }

            dq.addLast(nl);
        }

        return new State(dp[n], cnt[n]);
    }

    private Line makeLine(long[] pre, long[] dp, long[] cnt, int j) {
        long s = pre[j];
        long m = -s;
        long b = dp[j] + (s * s - s) / 2;
        return new Line(m, b, cnt[j]);
    }

    private long tri(long x) {
        return x * (x + 1) / 2;
    }

    private boolean betterOrEqual(Line a, Line b, long x) {
        long va = a.value(x);
        long vb = b.value(x);

        if (va != vb) return va < vb;

        // Important for WQS:
        // if same penalized cost, prefer more groups.
        return a.cnt >= b.cnt;
    }

    private boolean bad(Line l1, Line l2, Line l3) {
        // Slopes are decreasing.
        // Remove l2 if intersection(l1,l2) >= intersection(l2,l3).
        //
        // Use double here to avoid long overflow in cross multiplication.
        double x12 = (l2.b - l1.b) / (double) (l1.m - l2.m);
        double x23 = (l3.b - l2.b) / (double) (l2.m - l3.m);
        return x12 >= x23;
    }
}
