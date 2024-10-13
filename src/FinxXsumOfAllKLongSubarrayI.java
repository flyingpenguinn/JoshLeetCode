import java.util.PriorityQueue;

public class FinxXsumOfAllKLongSubarrayI {
    public int[] findXSum(int[] a, int k, int x) {
        int n = a.length;
        int[] res = new int[n - k + 1];
        int ri = 0;
        for (int i = 0; i + k - 1 < n; ++i) {
            int[] cnt = new int[101];

            for (int j = i; j < i + k && j < n; ++j) {
                ++cnt[a[j]];
            }
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((s, y) -> {
                int cx = s[0];
                int cy = y[0];
                if (cx != cy) {
                    return Integer.compare(cx, cy);
                } else {
                    return Integer.compare(s[1], y[1]);
                }
            });
            for (int j = 1; j <= 100; ++j) {
                if (cnt[j] == 0) {
                    continue;
                }
                pq.offer(new int[]{cnt[j], j});
                if (pq.size() > x) {
                    pq.poll();
                }
            }
            int sum = 0;
            while (!pq.isEmpty()) {
                int[] top = pq.poll();
                sum += top[0] * top[1];
            }
            res[ri++] = sum;
        }
        return res;
    }

}
