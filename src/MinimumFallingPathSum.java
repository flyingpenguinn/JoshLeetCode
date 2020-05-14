public class MinimumFallingPathSum {
    Integer[][] dp = null;

    public int minFallingPathSum(int[][] a) {
        dp = new Integer[a.length][a[0].length];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a[0].length; i++) {
            min = Math.min(min, doPath(a, 0, i));
        }
        return min;

    }

    private int doPath(int[][] a, int i, int j) {
        int cur = a[i][j];
        if (i == a.length - 1) {
            return cur;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        int next = doPath(a, i + 1, j);
        if (j + 1 < a[0].length) {
            next = Math.min(next, doPath(a, i + 1, j + 1));
        }
        if (j - 1 >= 0) {
            next = Math.min(next, doPath(a, i + 1, j - 1));
        }
        dp[i][j]= next +cur;
        return dp[i][j];
    }

    public static void main(String[] args) {

    }
}
