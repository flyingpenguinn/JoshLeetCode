/*
LC#48
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
 */
public class RotateImage {

    // transpose first then swap columns on each row
    public void rotate(int[][] a) {

        transpose(a);
        swapColumns(a);
    }

    private void transpose(int[][] a) {
        // because it's n by n, we can transpose in place... otherwise need to new one
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // from i! otherwise... numbers are transposed back to origin...
                swap(a, i, j, j, i);
            }
        }
    }

    private void swapColumns(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            int j = 0;
            int k = n - 1;
            while (j < k) {
                swap(a, i, j++, i, k--);
            }
        }
    }

    private void swap(int[][] a, int i, int j, int s, int t) {
        int tmp = a[i][j];
        a[i][j] = a[s][t];
        a[s][t] = tmp;
    }
}
