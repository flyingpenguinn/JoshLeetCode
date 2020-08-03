import java.util.List;

/*
LC#1428
(This problem is an interactive problem.)

A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.







Example 1:



Input: mat = [[0,0],[1,1]]
Output: 0
Example 2:



Input: mat = [[0,0],[0,1]]
Output: 1
Example 3:



Input: mat = [[0,0],[0,0]]
Output: -1
Example 4:



Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
Output: 1


Constraints:

rows == mat.length
cols == mat[i].length
1 <= rows, cols <= 100
mat[i][j] is either 0 or 1.
mat[i] is sorted in a non-decreasing way.
 */

interface BinaryMatrix {
    public int get(int x, int y);

    public List<Integer> dimensions();
}

public class LeftMostColWithAtleastOne {
    // note between rows, they are NOT sorted. but we can still trace the contour
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int m = dim.get(0);
        int n = dim.get(1);
        int i = 0;
        int j = n - 1;
        int lastj = n;
        while (j >= 0) {
            while (i < m && binaryMatrix.get(i, j) == 0) {
                i++;
            }
            if (i == m) {
                break;
            }
            // i, j is 1. now move to the next column
            lastj = Math.min(lastj, j);
            j--;
        }
        return lastj == n ? -1 : lastj;
    }

}


class LeftMostColumnAtleastOneBinarySearch {
    // do a binary search on each row, m*logn
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int m = dim.get(0);
        int n = dim.get(1);
        int res = n;
        for (int i = 0; i < m; i++) {
            int col = binarySearch(binaryMatrix, i, n);
            res = Math.min(res, col);
        }
        return res == n ? -1 : res;
    }

    // binary search row given column length
    private int binarySearch(BinaryMatrix bm, int row, int n) {
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (bm.get(row, mid) == 1) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l; // return n if all zeros
    }
}