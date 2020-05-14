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
        int m = a.length;
        if (m == 0) {
            return new int[0];
        }
        int n = a[0].length;
        int[] r = new int[m * n];
        int ri = 0;
        for (int sum = 0; sum <= m + n - 2; sum++) {
            if (sum % 2 == 0) {
                int rowstart = m - 1;
                int colstart = sum - rowstart;
                if (colstart < 0) {
                    rowstart += colstart;
                    colstart = 0;
                }
                int i = rowstart;
                int j = colstart;
                while (i >= 0 && i < m && j >= 0 && j < n) {
                    r[ri++] = a[i][j];
                    i--;
                    j++;
                }
            } else {
                int rowstart = 0;
                int colstart = sum - rowstart;
                if (colstart > (n - 1)) {
                    rowstart += colstart - (n - 1);
                    colstart = n - 1;
                }
                int i = rowstart;
                int j = colstart;
                while (i >= 0 && i < m && j >= 0 && j < n) {
                    r[ri++] = a[i][j];
                    i++;
                    j--;
                }
            }
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
        int m = a.length;
        if (m == 0) {
            return new int[0];
        }
        int n = a[0].length;

        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        int maxsum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = i + j;
                maxsum = Math.max(maxsum, sum);
                if (sum % 2 == 1) {
                    map.computeIfAbsent(sum, k -> new LinkedList<>()).offerLast(a[i][j]);
                } else {
                    map.computeIfAbsent(sum, k -> new LinkedList<>()).offerFirst(a[i][j]);
                }
            }
        }
        int[] r = new int[m * n];
        int ri = 0;
        for (int i = 0; i <= maxsum; i++) {
            LinkedList<Integer> v = map.get(i);
            Iterator<Integer> it = v.iterator();
            while (it.hasNext()) {
                r[ri++] = it.next();
            }
        }
        return r;
    }
}
