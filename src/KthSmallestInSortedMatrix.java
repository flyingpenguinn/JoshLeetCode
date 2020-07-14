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
        if (a == null || a.length == 0 || k <= 0) {
            return -1; // or throw
        }
        int m = a.length;
        int n = a[0].length;
        if (k > m * n) {
            return -1;
        }
        // r,c,v
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        pq.offer(new int[]{0, 0, a[0][0]});
        while (k > 1) {
            int[] top = pq.poll();
            int r = top[0];
            int c = top[1];
            if (c + 1 < n) {
                pq.offer(new int[]{r, c + 1, a[r][c + 1]});
            }
            if (r + 1 < m && c == 0) {
                pq.offer(new int[]{r + 1, c, a[r + 1][c]});
            }
            k--;
        }
        return pq.poll()[2];
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
        if(a==null || a.length==0 || k<=0){
            return -1; // or throw
        }
        int m = a.length;
        int n = a[0].length;
        int l = a[0][0];
        int u = a[m-1][n-1];
        while(l<=u){
            int mid = l+(u-l)/2;
            int count = count(a, mid); // how many nums <=k
            if(count>=k){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }

    // how many nums <=t
    private int count(int[][] a, int t){
        int m = a.length;
        int n = a[0].length;

        int i = 0;
        int j = n-1;
        int r = 0;
        while(j>=0){
            while(i<m && a[i][j]<=t){
                i++;
            }
            // 0.. i-1 on this j is known to be <=t
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