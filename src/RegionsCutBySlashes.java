/*
LC#959
In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.



Example 1:

Input:
[
  " /",
  "/ "
]
Output: 2
Explanation: The 2x2 grid is as follows:

Example 2:

Input:
[
  " /",
  "  "
]
Output: 1
Explanation: The 2x2 grid is as follows:

Example 3:

Input:
[
  "\\/",
  "/\\"
]
Output: 4
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
The 2x2 grid is as follows:

Example 4:

Input:
[
  "/\\",
  "\\/"
]
Output: 5
Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
The 2x2 grid is as follows:

Example 5:

Input:
[
  "//",
  "/ "
]
Output: 3
Explanation: The 2x2 grid is as follows:



Note:

1 <= grid.length == grid[0].length <= 30
grid[i][j] is either '/', '\', or ' '.
 */
public class RegionsCutBySlashes {
    /* make the grids discrete by enlarging to 9 times as big so that we have full cells to visit. note have to be 9 to avoid going to cells that
    can be diagonally connected
     */
    char[][] g;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    char X = 'X';

    public int regionsBySlashes(String[] a) {
        int rows = a.length;
        int cols = a[0].length();
        g = new char[rows * 3][cols * 3];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char gc = a[i].charAt(j);
                if (gc == ' ') {
                    g[3 * i][3 * j] = X;
                    g[3 * i][3 * j + 1] = X;
                    g[3 * i][3 * j + 2] = X;
                    g[3 * i + 1][3 * j] = X;
                    g[3 * i + 1][3 * j + 1] = X;
                    g[3 * i + 1][3 * j + 2] = X;
                    g[3 * i + 2][3 * j] = X;
                    g[3 * i + 2][3 * j + 1] = X;
                    g[3 * i + 2][3 * j + 2] = X;
                } else if (gc == '/') {
                    /*
                    XX/
                    X/X
                    /XX
                    */
                    g[3 * i][3 * j] = X;
                    g[3 * i][3 * j + 1] = X;
                    g[3 * i][3 * j + 2] = '/';
                    g[3 * i + 1][3 * j] = X;
                    g[3 * i + 1][3 * j + 1] = '/';
                    g[3 * i + 1][3 * j + 2] = X;
                    g[3 * i + 2][3 * j] = '/';
                    g[3 * i + 2][3 * j + 1] = X;
                    g[3 * i + 2][3 * j + 2] = X;
                } else {
                    /*
                    \XX
                    X\X
                    XX\
                    */
                    g[3 * i][3 * j] = '\\';
                    g[3 * i][3 * j + 1] = X;
                    g[3 * i][3 * j + 2] = X;
                    g[3 * i + 1][3 * j] = X;
                    g[3 * i + 1][3 * j + 1] = '\\';
                    g[3 * i + 1][3 * j + 2] = X;
                    g[3 * i + 2][3 * j] = X;
                    g[3 * i + 2][3 * j + 1] = X;
                    g[3 * i + 2][3 * j + 2] = '\\';
                }
            }
        }
        int r = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j] == X) {
                    dfs(i, j);
                    r++;
                }
            }
        }
        return r;
    }

    private void dfs(int i, int j) {
        g[i][j] = '*';
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (inRange(ni, nj) && g[ni][nj] == X) {
                dfs(ni, nj);
            }
        }
    }

    boolean inRange(int i, int j) {
        return i >= 0 && i < g.length && j >= 0 && j < g[0].length;
    }

    public static void main(String[] args) {
        String[] input = {"//", "/ "};
        System.out.println(new RegionsCutBySlashes().regionsBySlashes(input));
    }
}
