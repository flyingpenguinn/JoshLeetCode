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

    // because each row columns are sorted and each row is sorted, we can do O(m+n) like finding a value in a sorted matrix
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int m = dim.get(0);
        int n = dim.get(1);
        int j = n - 1;
        int i = 0;
        int lastj = -1;
        while (j >= 0) {
            while (i < m && binaryMatrix.get(i, j) == 0) {
                i++;
            }
            if (i == m) {
                break;
            }
            // i,j is 1 that's why we stopped
            lastj = j;
            j--;
        }
        return lastj;
    }

}


class LeftMostColumnAtleastOneBinarySearch {
    public int leftMostColumnWithOne(BinaryMatrix bm) {
        List<Integer> dim = bm.dimensions();
        int m = dim.get(0);
        int n = dim.get(1);
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            boolean found = false;
            for (int i = 0; i < m; i++) {
                if (bm.get(i, mid) == 1) {
                    found = true;
                    break;
                }

            }
            if (found) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // binary search when there is a possbility of nothing found, make sure we can handle it!
        return l == n ? -1 : l;
    }
}