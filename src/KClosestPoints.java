import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
LC#973
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)


Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
 */
public class KClosestPoints {
    public int[][] kClosest(int[][] ps, int k) {
        // big heap on distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });
        for (int[] p : ps) {
            pq.offer(new int[]{dist(p), p[0], p[1]});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[][] r = new int[pq.size()][2];
        int ri = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            r[ri][0] = top[1];
            r[ri][1] = top[2];
            ri++;
        }
        return r;
    }

    private int dist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}

class KClosestPointsSelection {
    // select the kth and partition it
    // when we get the kth, due to partition,we also get the first ks
    public int[][] kClosest(int[][] ps, int k) {
        dok(ps, 0, ps.length - 1, k);

        int[][] r = new int[k][2];
        for (int i = 0; i < k; i++) {
            r[i] = ps[i];
        }
        return r;
    }

    private void dok(int[][] ps, int l, int u, int k) {
        if (l >= u) {
            return;
        }
        int pivot = partition(ps, l, u);
        int llen = pivot - l + 1; // !!! pay attention to this!
        if (k == llen) {
            // pivot is the kth
            return;
        } else if (k < llen) {
            dok(ps, l, pivot - 1, k);
        } else {
            dok(ps, pivot + 1, u, k - llen);
        }
    }

    private int partition(int[][] ps, int l, int u) {
        int i = l - 1;
        int j = l;
        while (j <= u) {
            if (dist(ps[j]) <= dist(ps[u])) {
                swap(ps, ++i, j);
            }
            j++;
        }
        return i;
    }

    private void swap(int[][] ps, int i, int j) {
        int[] tmp = ps[i];
        ps[i] = ps[j];
        ps[j] = tmp;
    }

    private int dist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}
