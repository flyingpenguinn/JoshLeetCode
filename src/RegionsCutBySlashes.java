import java.util.Arrays;

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
    /* make the grids discrete by enlarging to 9 (3*3) times as big so that we have full cells to visit. note have to be 9 to avoid going to cells that
    can be diagonally connected
     */
    public int regionsBySlashes(String[] input) {
        int n = input.length;
        char[][] g = new char[3*n][3*n];
        for(int i=0; i<3*n; i++){
            Arrays.fill(g[i], ' ');
        }
        for(int i=0; i<3*n; i+=3){
            for(int j=0; j<3*n; j+=3){
                map(g, i, j, input, i/3, j/3);
            }
        }
        //  System.out.println(Arrays.deepToString(g));
        boolean[][] seen = new boolean[3*n][3*n];
        int res = 0;
        for(int i=0; i<3*n; i++){
            for(int j=0; j<3*n; j++){
                if(g[i][j]==' ' && !seen[i][j]){
                    //  System.out.println(i+" "+j);
                    dfs(g, i, j, seen);
                    res++;
                }
            }
        }
        return res;
    }

    private void map(char[][] g, int i, int j, String[] input, int oi,  int oj){
        char oc = input[oi].charAt(oj);
        if(oc == '/'){
            g[i][j+2] = '/';
            g[i+1][j+1] = '/';
            g[i+2][j] = '/';
        }else if(oc == '\\'){
            g[i][j] = '\\';
            g[i+1][j+1] = '\\';
            g[i+2][j+2] = '\\';
        }
    }

    private int[][] dirs = {{-1,0}, {1,0}, {0, -1}, {0,1}};

    private void dfs(char[][] g, int i, int j, boolean[][] seen){
        //  System.out.println("visiting "+i+" "+j);
        seen[i][j] = true;
        for(int[] d: dirs){
            int ni = i+d[0];
            int nj = j+d[1];
            if(ni>=0 && ni<g.length && nj>=0 && nj<g[0].length && g[ni][nj] == ' ' && !seen[ni][nj]){
                dfs(g,ni, nj, seen);
            }
        }
    }
}
