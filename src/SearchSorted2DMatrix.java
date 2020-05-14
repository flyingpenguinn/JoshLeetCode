import java.util.Arrays;

/*
LC#74
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
 */
public class SearchSorted2DMatrix {

    // logm + logn, better than O(m+n) solutions...
    public boolean searchMatrix(int[][] a, int t) {
        int m = a.length;
        if (m == 0 || a[0].length == 0) {
            return false;
        }
        int[] rows = new int[m];
        for (int i = 0; i < m; i++) {
            rows[i] = a[i][0];
        }
        int row = Arrays.binarySearch(rows, t);
        if (row >= 0) {
            return true;
        }
        //  System.out.println(row);
        int ins = -row - 2;
        if (ins < 0) {
            return false;
        }
        return Arrays.binarySearch(a[ins], t) >= 0;
    }
}
