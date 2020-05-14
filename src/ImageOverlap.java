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

    // both this and below are On^4
    public int largestOverlap(int[][] a, int[][] b) {
        int n = a.length;
        int max = 0;
        for (int i = -(n - 1); i < n; i++) {
            for (int j = -(n - 1); j < n; j++) {
                int ov = count(a, b, i, j);
                //   System.out.println(i+" "+j+" "+ov);
                max = Math.max(max, ov);
            }
        }
        return max;
    }

    int count(int[][] a, int[][] b, int rm, int cm) {
        int n = a.length;
        int r = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // supplements with 0...
                int ib = i + rm;
                int jb = j + cm;
                if (ib < 0 || ib >= n || jb < 0 || jb >= n) {
                    continue;
                }
                if (a[i][j] == b[ib][jb] && a[i][j] == 1) {
                    r++;
                }
            }
        }
        return r;
    }
}

class ImageOverlapSparse {
    // only pick out ones then group the moves
    Set<int[]> as = new HashSet<>();
    Set<int[]> bs = new HashSet<>();
    int mask = 100;

    public int largestOverlap(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    as.add(new int[]{i, j});
                }
                if (b[i][j] == 1) {
                    bs.add(new int[]{i, j});
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int[] ai : as) {
            for (int[] bi : bs) {
                int row = bi[0] - ai[0];
                int col = bi[1] - ai[1];
                int move = row * mask + col;
                int nc = map.getOrDefault(move, 0) + 1;
                max = Math.max(max, nc);
                map.put(move, nc);
            }
        }
        return max;
    }
}
