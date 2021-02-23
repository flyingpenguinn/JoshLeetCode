import base.ArrayUtils;

/*
LC#240
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */
public class SearchSorted2DMatrixII {
    // O(m+n)
    // upper right is the "special one" that all on the same row are smaller,
    // all on the col are bigger. so we can throw one row or col effectively
    public boolean searchMatrix(int[][] a, int t) {
        int m = a.length;
        int n = a[0].length;
        int i = 0;
        int j = n-1;
        while(i<m && j>=0){
            if(a[i][j]==t){
                return true;
            }else if(a[i][j]>t){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.read("[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]");
        System.out.println(new SearchSorted2DMatrixII().searchMatrix(matrix, 6));
    }
}

class SearchSortedMatrixLinear {
    // each search throws away 1/4 of the matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return doBinary(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, target);
    }

    private boolean doBinary(int[][] matrix, int i, int j, int r, int c, int target) {
        if (i > r || j > c) {
            return false;
        }
        int midr = i + (r - i) / 2;
        int midc = j + (c - j) / 2;
        if (matrix[midr][midc] == target) {
            return true;
        }
        // rest fo the matrix is L shaped so can be cut into 2 rectangles...
        if (matrix[midr][midc] > target) {
            // throw away bottom right
            return doBinary(matrix, i, j, midr - 1, c, target) || doBinary(matrix, midr, j, r, midc - 1, target);
        } else {
            // throw away upper left
            return doBinary(matrix, midr + 1, j, r, c, target) || doBinary(matrix, i, midc + 1, midr, c, target);
        }
    }
}
