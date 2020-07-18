import base.ArrayUtils;

import java.util.*;

/*
LC#498
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.



Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:



Note:

The total number of elements of the given matrix will not exceed 10,000.
 */
public class DiagnalTraverse {
    // traverse according to sum of i, j.  starting point is either m-1 or 0. when it yields bad col, we just move the row over to good area
    public int[] findDiagonalOrder(int[][] a) {
        // if over right border? bottom border? top border? left border? deal with them differently.
        // note we must deal with right/bottom first then top/eft for the processing of diagnoals. diagonals follow the right/bottom way
        if (a == null || a.length == 0 || a[0].length == 0) {
            return new int[0];
        }
        int m = a.length;
        int n = a[0].length;
        int i = 0;
        int j = 0;
        int[] r = new int[m * n];
        int ri = 0;
        int dir = 1;
        while (ri < m * n) {
            if (j == n) {
                // must do right/bottom first due to diagonal handling is more like the right/bottom way
                j--;
                i += 2;
                dir = -dir;
            } else if (i == m) {
                i--;
                j += 2;
                dir = -dir;
            } else if (j == -1) {
                j++;
                dir = -dir;
            } else if (i == -1) {
                i++;
                dir = -dir;
            }
            r[ri++] = a[i][j];
            i -= dir;
            j += dir;

        }
        return r;
    }

    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.read("[[1,2,3],[4,5,6],[7,8,9]]");
        System.out.println(Arrays.toString(new DiagnalTraverse().findDiagonalOrder(matrix)));
    }
}

class DiagonalTraverseMine {
    // or we can use a map to keep the sums
    public int[] findDiagonalOrder(int[][] a) {
        if (a == null) {
            return new int[0];
        }
        int m = a.length;
        int n = a[0].length;
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = i + j;
                if (key % 2 == 1) {
                    map.computeIfAbsent(key, k -> new LinkedList<>()).offerLast(a[i][j]);
                } else {
                    map.computeIfAbsent(key, k -> new LinkedList<>()).offerFirst(a[i][j]);
                }
            }
        }
        int[] r = new int[m * n];
        int ri = 0;
        for (int i = 0; i <= m + n - 2; i++) {
            LinkedList<Integer> list = map.get(i);
            while (!list.isEmpty()) {
                r[ri++] = list.pollFirst();
            }
        }
        return r;
    }
}
