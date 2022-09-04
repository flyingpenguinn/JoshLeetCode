import java.util.ArrayDeque;
import java.util.Deque;

public class MaxRobotsWithinBudget {
    public int maximumRobots(int[] a, int[] rc, long b) {
        int n = a.length;
        int l = 1;
        int u = n;
        long[] sum = new long[n];
        for (int i = 0; i < n; ++i) {
            sum[i] = (i == 0 ? 0L : sum[i - 1]) + rc[i];
        }
        while (l <= u) {
            int k = l + (u - l) / 2;
            Deque<Integer> dq = new ArrayDeque<>();
            boolean good = false;
            for (int i = 0; i < n; ++i) {
                int head = i - k + 1;
                while (!dq.isEmpty() && dq.peekFirst() < head) {
                    dq.pollFirst();
                }
                while (!dq.isEmpty() && a[dq.peekLast()] <= a[i]) {
                    dq.pollLast();
                }
                dq.offerLast(i);
                if (head >= 0) {
                    long cur1 = a[dq.peekFirst()];
                    long cur2 = sum[i] - (head == 0 ? 0 : sum[head - 1]);
                    long cur = cur1 + k * cur2;
                    //   System.out.println("k="+k+" "+head+"..."+i+" "+cur1+" "+cur2);
                    if (cur <= b) {
                        good = true;
                        break;
                    }
                }

            }
            if (good) {
                l = k + 1;
            } else {
                u = k - 1;
            }
        }
        return u;
    }
}
