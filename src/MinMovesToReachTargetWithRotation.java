import base.ArrayUtils;

// could also use bfs as this is asking for shortest path in unweighted graph
public class MinMovesToReachTargetWithRotation {

    public int minimumMoves(int[][] g) {
        dp = new int[g.length][g[0].length][2];
        int rt = dom(0, 1, 0, g);
        return rt >= Max ? -1 : rt;
    }

    int[][][] dp;

    int Max = 1000000;

    // i,j is the head of the snake. face==0 is right, ==1 is down
    private int dom(int i, int j, int face, int[][] g) {
        int rows = g.length;
        int cols = g[0].length;
        if (i == rows - 1 && j == cols - 1 && face == 0) {
            return 0;
        }
        if (dp[i][j][face] != 0) {
            return dp[i][j][face];
        }
        // avoid later steps search on this state. for example snake can go clockwise first then counter clockwise immediately after
        dp[i][j][face] = Max;
        int goright = Max;
        if (face == 0 && j + 1 < cols && g[i][j + 1] == 0) {
            goright = 1 + dom(i, j + 1, face, g);
        }
        if (face == 1 && j + 1 < cols && g[i][j + 1] == 0 && g[i - 1][j + 1] == 0) {
            goright = 1 + dom(i, j + 1, face, g);
        }
        int godown = Max;
        if (face == 1 && i + 1 < rows && g[i + 1][j] == 0) {
            godown = 1 + dom(i + 1, j, face, g);
        }
        if (face == 0 && i + 1 < rows && g[i + 1][j] == 0 && g[i + 1][j - 1] == 0) {
            godown = 1 + dom(i + 1, j, face, g);
        }
        int clockwise = Max;
        if (face == 0 && i + 1 < rows && g[i + 1][j] == 0 && g[i + 1][j - 1] == 0) {
            clockwise = 1 + dom(i + 1, j - 1, 1, g);
        }
        int cclockwise = Max;
        if (face == 1 && j + 1 < cols && g[i][j + 1] == 0 && g[i - 1][j + 1] == 0) {
            clockwise = 1 + dom(i - 1, j + 1, 0, g);
        }
        int rt = Math.min(goright, Math.min(godown, Math.min(clockwise, cclockwise)));
        dp[i][j][face] = rt;
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(new MinMovesToReachTargetWithRotation().minimumMoves(ArrayUtils.read("[[0,0,1,1,1,1],\n" +
                "               [0,0,0,0,1,1],\n" +
                "               [1,1,0,0,0,1],\n" +
                "               [1,1,1,0,0,1],\n" +
                "               [1,1,1,0,0,1],\n" +
                "               [1,1,1,0,0,0]]")));
    }
}
