import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

public class MinOperationsToMakeElementsWithINKsubarraysEQUAL {
    private static class MyPq {
        private int size = 0;
        private long sum = 0;
        private TreeMap<Long, Long> tm;

        private MyPq(boolean smallTop) {
            if (smallTop) {
                tm = new TreeMap<>();
            } else {
                tm = new TreeMap<>(Collections.reverseOrder());
            }
        }

        public long poll() {
            --size;
            if (tm.isEmpty()) {
                throw new IllegalStateException("Empty pq");
            } else {
                long top = tm.firstKey();
                update(tm, top, -1);
                return top;
            }
        }

        public void offer(Long k) {
            ++size;
            update(tm, k, 1);

        }

        private void update(TreeMap<Long, Long> tm, Long k, long d) {

            long nv = tm.getOrDefault(k, 0L) + d;
            if (nv <= 0) {
                tm.remove(k);

            } else {
                tm.put(k, nv);
            }
            sum += d * k;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public int size() {
            return size;
        }

        public boolean contains(Long k) {
            return tm.containsKey(k);
        }

        public void remove(Long k) {
            --size;
            update(tm, k, -1);
        }

        public Long top() {
            return tm.firstKey();
        }
    }

    protected void adjustMyPq() {
        if (pq1.size() > pq2.size() + 1) {
            pq2.offer(pq1.poll());
        } else if (pq1.size() < pq2.size()) {
            pq1.offer(pq2.poll());
        }
    }

    MyPq pq1 = new MyPq(false);
    MyPq pq2 = new MyPq(true);
    long[] ops;

    public long minOperations(int[] a, int x, int k) {
        int n = a.length;
        long sum = 0;
        ops = new long[n];
        Arrays.fill(ops, Max);
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            if (!pq1.isEmpty() && v > pq1.top()) {
                pq2.offer(v);
            } else {
                pq1.offer(v);
            }
            adjustMyPq();
            sum += v;
            if (i - x + 1 >= 0) {
                int head = i - x + 1;
                long mid = pq1.top();
                long cop1 = (mid * pq1.size) - pq1.sum;
                long cop2 = pq2.sum - (mid * pq2.size);
                ops[head] = cop1 + cop2;
                long vhead = a[head];
                sum -= vhead;
                if (pq1.contains(vhead)) {
                    pq1.remove(vhead);
                } else {
                    pq2.remove(vhead);

                }
                adjustMyPq();
            }
        }
        dp = new long[n][k + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(a, 0, k, x);
    }

    private long Max = (long) 1e18;
    private long[][] dp;

    private long solve(int[] a, int i, int k, int x) {
        int n = a.length;
        if (k == 0) {
            return 0;
        }
        if (i >= n) {
            return Max;
        }
        if (dp[i][k] != -1) {
            return dp[i][k];
        }
        long way1 = solve(a, i + 1, k, x);
        long way2 = ops[i] + solve(a, i + x, k - 1, x);
        long res = Math.min(way1, way2);
        dp[i][k] = res;
        return res;
    }
}
