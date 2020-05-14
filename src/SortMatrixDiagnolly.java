import java.util.*;

/*
LC#1329
Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.



Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 */
public class SortMatrixDiagnolly {
    // numbers on the same diag share the same i-j so put them to a sorted structure
    public int[][] diagonalSort(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                PriorityQueue<Integer> pq = map.getOrDefault(i - j, new PriorityQueue<>());
                pq.offer(a[i][j]);
                map.put(i - j, pq);
            }
        }
        // from high to low left to right
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = map.get(i - j).poll();
            }
        }
        return a;
    }
}
