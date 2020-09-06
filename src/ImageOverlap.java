import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#835
Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes:

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
 */
public class ImageOverlap {

    // both this and below are On^4 in the worst case, but the 2nd one is better for sparse matrix
    public int largestOverlap(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        int max = 0;
        for (int rowMove = -m; rowMove <= m; rowMove++) {
            for (int colMove = -n; colMove <= n; colMove++) {
                int overlap = 0;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int row = i + rowMove;
                        int col = j + colMove;
                        int value = 0;
                        if (row < m && row >= 0 && col < n && col >= 0) {
                            value = a[row][col];
                            if (value == b[i][j] && value == 1) {
                                overlap++;
                            }
                        }
                    }
                }
                max = Math.max(max, overlap);
            }
        }
        return max;
    }
}

class ImageOverlapSparse {
    // what are the asks from each "1"?
    // only pick out ones then group the desired moves.
    public int largestOverlap(int[][] a, int[][] b) {
        Set<int[]> as = new HashSet<>();
        Set<int[]> bs = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 1) {
                    as.add(new int[]{i, j});
                }
                if (b[i][j] == 1) {
                    bs.add(new int[]{i, j});
                }
            }
        }
        Map<Integer, Integer> m = new HashMap<>();
        int max = 0;
        for (int[] ai : as) {
            for (int[] bi : bs) {
                int drow = bi[0] - ai[0];
                int dcol = bi[1] - ai[1];
                int code = drow * 100 + dcol;
                int nv = m.getOrDefault(code, 0) + 1;
                m.put(code, nv);
                max = Math.max(max, nv);
            }
        }
        return max;
    }
}
