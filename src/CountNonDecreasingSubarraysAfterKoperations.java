import java.util.*;

public class CountNonDecreasingSubarraysAfterKoperations {
    // 2026-11-12 daily: reverse two pointers. cost depends on prefix max from the left,
    // so i moves leftwards (add on the left = merge monotonic blocks, amortized O(1))
    // and j shrinks from the right (remove on the right = O(1), a[j] is in the last block).
    public long countNonDecreasingSubarrays(int[] a, int k) {
        Deque<int[]> dq = new ArrayDeque<>();
        int n = a.length;
        int j = n - 1;
        long res = 0;
        long cost = 0;
        for (int i = n - 1; i >= 0; --i) {
            int v = a[i];
            int[] block = {v, 1}; // value, length

            while (!dq.isEmpty() && dq.peekFirst()[0] <= v) {
                int[] first = dq.pollFirst();
                long delta = (v - first[0]);
                int count = first[1];
                cost += delta * count;
                block[1] += count;
            }
            dq.push(block);
            while (!dq.isEmpty() && j >= i && cost > k) {
                long thrown = dq.peekLast()[0] - a[j];
                cost -= thrown;
                --dq.peekLast()[1];
                if (dq.peekLast()[1] == 0) {
                    dq.pollLast();
                }
                --j;
            }
            // i.. j good
            long len = j - i + 1;
            res += len;
        }
        return res;
    }

    public static void main(String[] args) {
        Random rd = new Random(7);
        int bad = 0;
        for (int t = 0; t < 3000; t++) {
            int n = 1 + rd.nextInt(8);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = 1 + rd.nextInt(6);
            int k = rd.nextInt(8);
            long exp = 0;
            for (int l = 0; l < n; l++) {
                long mx = 0, c = 0;
                for (int r = l; r < n; r++) {
                    mx = Math.max(mx, a[r]);
                    c += mx - a[r];
                    if (c <= k) exp++;
                }
            }
            if (exp != new CountNonDecreasingSubarraysAfterKoperations().countNonDecreasingSubarrays(a, k)) bad++;
        }
        System.out.println("bad=" + bad);
    }
}
