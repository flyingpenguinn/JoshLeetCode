import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*
LC#1284
Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

Binary matrix is a matrix with all cells equal to 0 or 1 only.

Zero matrix is a matrix with all cells equal to 0.



Example 1:


Input: mat = [[0,0],[0,1]]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
Example 2:

Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We don't need to change it.
Example 3:

Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
Output: 6
Example 4:

Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix can't be a zero matrix


Constraints:

m == mat.length
n == mat[0].length
1 <= m <= 3
1 <= n <= 3
mat[i][j] is 0 or 1.
 */
public class MinNumberOfFlipsToConvertBinaryMatrix {

    class Item {
        String m;
        int steps;

        public Item(String m, int steps) {
            this.m = m;
            this.steps = steps;
        }
    }

    private String encode(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        String r = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                r += m[i][j];
            }
        }
        return r;
    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minFlips(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        Queue<Item> q = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        String encode = encode(m);
        seen.add(encode);
        q.offer(new Item(encode, 0));
        while (!q.isEmpty()) {
            Item top = q.poll();
            String topm = top.m;
            if (Integer.valueOf(topm) == 0) {
                return top.steps;
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    StringBuilder sb = new StringBuilder(topm);
                    int pos = i * cols + j;
                    sb.setCharAt(pos, topm.charAt(pos) == '0' ? '1' : '0');
                    for (int[] d : dirs) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                            pos = ni * cols + nj;
                            sb.setCharAt(pos, topm.charAt(pos) == '0' ? '1' : '0');
                        }
                    }
                    String ns = sb.toString();
                    if (!seen.contains(ns)) {
                        seen.add(ns);
                        q.offer(new Item(ns, top.steps + 1));
                    }
                }
            }
        }
        return -1;
    }
}
