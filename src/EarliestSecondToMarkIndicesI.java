import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class EarliestSecondToMarkIndicesI {
    // just decrement the one that is closest to its last chance
    public int earliestSecondToMarkIndices(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int l = 0;
        int u = m;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(a, b, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }

    private boolean good(int[] a, int[] b, int mid) {
        int n = a.length;
        int m = b.length;
        int[] last = new int[n];
        Arrays.fill(last, -1);
        for (int i = 0; i < mid; ++i) {
            last[b[i] - 1] = i;
        }

        Set<Integer> marked = new HashSet<>();
        Set<Integer> zeros = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (last[i] == -1 && a[i] > 0) {
                return false;
            }
            if (a[i] == 0) {
                zeros.add(i);
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(last[x[1]], last[y[1]]));
        for (int i = 0; i < n; ++i) {
            if (a[i] != 0) {
                pq.offer(new int[]{a[i], i});
            }
        }
        for (int i = 0; i < mid && marked.size() < n; ++i) {
            if (last[b[i] - 1] == i && zeros.contains(b[i] - 1)) {
                marked.add(b[i] - 1);
                continue;
            } else {
                if (pq.isEmpty()) {
                    continue;
                }
                int[] top = pq.poll();
                int index = top[1];
                --top[0];
                if (top[0] == 0) {
                    zeros.add(index);
                } else {
                    pq.offer(top);
                }
            }
        }
        return marked.size() == n;
    }
}
