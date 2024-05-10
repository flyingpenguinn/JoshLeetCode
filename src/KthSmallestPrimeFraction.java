import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestPrimeFraction {

    private double eps = 0.000001;

    public int[] kthSmallestPrimeFraction(int[] a, int k) {
        int n = a.length;
        double l = 1.0 / a[n - 1];
        double u = (a[n - 1] - 1) * 1.0 / a[n - 1];
        double lastgood = -1.0;
        while (u - l > eps * eps) {
            double mid = (l + u) / 2;
            int i = n - 2;
            int j = n - 1;
            int smaller = 0;
            while (i >= 0) {
                while (j >= 0 && a[i] * 1.0 / a[j] <= mid) {
                    j--;
                }
                // 0..j-1
                smaller += n - 1 - j;
                // 0..j-1
                i--;
            }
            // <= has nobigger elements
            if (smaller >= k) {
                u = mid;
                lastgood = mid;
            } else {
                l = mid;
            }
        }

        for (int i = 1; i < n; i++) {
            double ddem = lastgood * a[i];
            int idem = (int) ddem;
            double diff = Math.abs(ddem - idem);
            if (idem > 0 && diff <= 0.000001) {
                return new int[]{idem, a[i]};
            }
        }
        return null;
    }
}

class KthSmallestPrimeFractionHeap {

    public int[] kthSmallestPrimeFraction(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(a[x[0]] * a[y[1]], a[y[0]] * a[x[1]]));
        pq.offer(new int[]{0, n - 1});
        int[] last = null;
        while (k > 0) {
            last = pq.poll();
            k--;
            int nj = last[1] - 1;
            if (nj >= 0 ) {
                pq.offer(new int[]{last[0], nj});
            }
            if (last[1] == n - 1) {
                int ni = last[0] + 1;
                if (ni < n ) {
                    pq.offer(new int[]{ni, last[1]});
                }
            }
        }
        return new int[]{a[last[0]], a[last[1]]};
    }
}