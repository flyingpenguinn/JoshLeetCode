import base.ArrayUtils;

import java.util.*;

/*
LC#378
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class KthSmallestInSortedMatrix {
    // klogk similar to LC#373. actually 373 can be converted to this question
    public int kthSmallest(int[][] a, int k) {
        int m = a.length;
        if (m == 0 || k == 0) {
            return -1;
        }
        int n = a[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(a[x[0]][x[1]], a[y[0]][y[1]]));
        pq.offer(new int[]{0, 0});
        int rem = k;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            rem--;
            if (rem == 0) {
                return a[top[0]][top[1]];
            }
            if (top[1] + 1 < n) {
                pq.offer(new int[]{top[0], top[1] + 1});
            }
            if (top[1] == 0 && top[0] + 1 < m) {
                pq.offer(new int[]{top[0] + 1, top[1]});
            }
        }
        return -1;
    }
}

class KthSmallestSortedMatrixBfs {
   // similar to 373's bfs.
    public int kthSmallest(int[][] a, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        int m = a.length;
        int n = a[0].length;
        int[] input = new int[m + 1];
        input[0] = a[0][0];
        q.offer(input);
        Set<String> visited = new HashSet<>();
        String indexstring = code(0, 0);
        visited.add(indexstring);
        while (k > 0) {
            int[] top = q.poll();
            k--;
            if (k == 0) {
                return top[0];
            }
            if (top[1] + 1 < m) {
                int ni = top[1] + 1;
                int nj = top[2];
                process(a, q, visited, ni, nj);
            }
            if (top[2] + 1 < n) {
                int ni = top[1];
                int nj = top[2] + 1;
                process(a, q, visited, ni, nj);
            }
        }
        return -1;
    }

    protected void process(int[][] a, PriorityQueue<int[]> q, Set<String> visited, int ni, int nj) {
        String code = code(ni, nj);
        if (!visited.contains(code)) {
            visited.add(code);
            q.offer(new int[]{a[ni][nj], ni, nj});
        }
    }

    private String code(int i, int j) {
        return i + "," + j;
    }
}

class KthSmallestSortedMatrixBinarySearch {
    // similar to find duplicated number, search number, not range. unlike search in sorted matrix!
    public int kthSmallest(int[][] a, int k) {
        int l = a[0][0];
        int m = a.length;
        if (m == 0 || k == 0) {
            return -1;
        }
        int n = a[0].length;
        int u = a[m - 1][n - 1];
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (kth(a, mid) >= k) {
                u = mid - 1; // squeeze out the solution
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int kth(int[][] a, int mid) {
        int i = 0;
        int j = a[0].length - 1;
        int r = 0;
        while (j >= 0) {
            while (i < a.length && a[i][j] <= mid) {
                i++;
            }
            // 0... i-1 in column j <= mid
            r += i;
            j--;
        }
        return r;
    }

    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.read("[[1,1][1,2]]");
        System.out.println(new KthSmallestSortedMatrixBinarySearch().kthSmallest(matrix, 2));
    }

}