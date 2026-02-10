import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestPrimeFraction {

    public int[] kthSmallestPrimeFraction(int[] a, int k) {
        int n = a.length;
        double l = 0;
        double u = (int) 3e4;
        while (l <= u) {
            double mid = l + (u - l) / 2.0;
            //   System.out.println("mid="+mid);
            double maxd = -1;
            int[] best = {-1, -1};
            int cur = 0;
            int j = 0;
            for (int i = 0; i < n; ++i) {
                while (j < n && a[i] * 1.0 / a[j] > mid) {
                    ++j;
                }
                cur += n - j;
                if (j < n) {
                    double cmax = a[i] * 1.0 / a[j];
                    //    System.out.println("i="+i+" cur="+cur+" cmax="+cmax);
                    if (cmax > maxd) {
                        maxd = cmax;
                        best = new int[]{a[i], a[j]};
                    }
                }
            }
            if (cur == k) {
                return best;
            } else if (cur < k) {
                l = mid;
            } else {
                u = mid;
            }
        }
        return new int[]{-1, -1};
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
            if (nj >= 0) {
                pq.offer(new int[]{last[0], nj});
            }
            if (last[1] == n - 1) {
                int ni = last[0] + 1;
                if (ni < n) {
                    pq.offer(new int[]{ni, last[1]});
                }
            }
        }
        return new int[]{a[last[0]], a[last[1]]};
    }
}