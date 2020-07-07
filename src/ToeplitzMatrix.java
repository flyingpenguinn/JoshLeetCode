import java.util.HashMap;
import java.util.Map;

/*
LC#766
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

Now given an M x N matrix, return True if and only if the matrix is Toeplitz.


Example 1:

Input:
matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
Output: True
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.
Example 2:

Input:
matrix = [
  [1,2],
  [2,2]
]
Output: False
Explanation:
The diagonal "[1, 2]" has different elements.

Note:

matrix will be a 2D array of integers.
matrix will have a number of rows and columns in range [1, 20].
matrix[i][j] will be integers in range [0, 99].

Follow up:

What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
What if the matrix is so large that you can only load up a partial row into the memory at once?
 */
public class ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return false;
        }

        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a[0].length; j++) {
                if (a[i][j] != a[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

class ToeplitzMatrixMapBased {
    // can work for follow ups. O(m+n) in space consumption
    public boolean isToeplitzMatrix(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return false;
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                Integer ext = m.get(i - j);
                if (ext == null) {
                    m.put(i - j, a[i][j]);
                } else if (ext != a[i][j]) {
                    return false;
                }

            }
        }
        return true;
    }
}
