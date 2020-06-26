import java.util.Arrays;
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
    public int[][] kClosest(int[][] a, int k) {
        int n = a.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Long.compare(dist(y), dist(x)));
        // must be big heap to get smallest k elements
        for (int i = 0; i < n; i++) {
            pq.offer(a[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[][] r = new int[pq.size()][2];
        for (int i = 0; i < r.length; i++) {
            r[i] = pq.poll();
        }
        return r;
    }

    long dist(int[] x) {
        return x[0] * x[0] + x[1] * x[1];
    }
}

class KClosestPointsSelection {
    // select the kth and partition it
    // when we get the kth, due to partition,we also get the first ks
    public int[][] kClosest(int[][] a, int k) {
        find(a, 0, a.length - 1, k);
        int[][] r = Arrays.copyOf(a, k);
        return r;
    }

    private void find(int[][] a, int l, int u, int k) {
        if (l >= u) { // k would be 1
            return;
        }
        int pivot = partition(a, l, u);
        int plen = pivot - l + 1;
        if (plen == k) {
            return;
        } else if (plen < k) {
            find(a, pivot + 1, u, k - plen);
        } else {
            // plen>k
            find(a, l, pivot - 1, k);
        }
    }

    private int partition(int[][] a, int l, int u) {
        int i = l - 1;
        int j = l;
        int distu = dist(a, u);
        while (j <= u) {
            if (dist(a, j) <= distu) {
                swap(a, ++i, j);
            }
            j++;
        }
        return i;
    }

    private int dist(int[][] a, int i) {
        return a[i][0] * a[i][0] + a[i][1] * a[i][1];
    }

    private void swap(int[][] a, int i, int j) {
        int[] tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
