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
// reverse rows then transpose
    /*anticlockwise rotate
  first reverse left to right, then swap the symmetry*/

    public void rotate(int[][] a) {
        reverserows(a);

        transpose(a);
    }

    void transpose(int[][] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(a, i, j, j, i);
            }
        }
    }

    void reverserows(int[][] a) {
        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            swap(a, i++, j--);
        }
    }

    void swap(int[][] a, int i, int j, int x, int y) {
        int tmp = a[i][j];
        a[i][j] = a[x][y];
        a[x][y] = tmp;
    }

    void swap(int[][] a, int i, int j) {
        int[] tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

class RotateImageAnotherWay {

    // keep swapping i,j to j,n-1-i
    public void rotate(int[][] a) {
        int n = a.length;
        int start = 0;
        int end = n - 1;
        for (int i = 0; i < n; i++) {
            for (int j = start; j < end; j++) {
                int ni = i;
                int nj = j;
                do {
                    int tmp = ni;
                    ni = nj;
                    nj = n - 1 - tmp;
                    swap(a, i, j, ni, nj);
                } while (ni != i || nj != j);
            }
            start++;
            end--;
        }

    }

    void swap(int[][] a, int i, int j, int ni, int nj) {
        int tmp = a[i][j];
        a[i][j] = a[ni][nj];
        a[ni][nj] = tmp;
    }
}