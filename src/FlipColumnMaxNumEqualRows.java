import base.ArrayUtils;

import java.util.*;

/*
LC#1072
Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.

Return the maximum number of rows that have all values equal after some number of flips.



Example 1:

Input: [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.


Note:

1 <= matrix.length <= 300
1 <= matrix[i].length <= 300
All matrix[i].length's are equal
matrix[i][j] is 0 or 1
 */
public class FlipColumnMaxNumEqualRows {

    // same AND complement rows form a group. find the biggest group
    public int maxEqualRowsAfterFlips(int[][] a) {
        Map<List<Integer>, Integer> zm = new HashMap<>();

        int r = 1;
        int same = 0;
        for (int i = 0; i < a.length; i++) {
            List<Integer> zs = new ArrayList<>();
            List<Integer> os = new ArrayList<>();
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 0) {
                    zs.add(j);
                } else {
                    os.add(j);
                }
            }
            int nv1 = zm.getOrDefault(zs, 0) + 1;
            int nv2 = zm.getOrDefault(os, 0);
            int cur = nv1 + nv2;
            r = Math.max(r, cur);
            zm.put(zs, nv1);

        }
        return Math.max(r, same);
    }

    public static void main(String[] args) {
        System.out.println(new FlipColumnMaxNumEqualRows().maxEqualRowsAfterFlips(ArrayUtils.read("[[0,0,0],[0,0,1],[1,1,0]]")));
    }
}
