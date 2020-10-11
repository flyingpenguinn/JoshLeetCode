public class MaxNetworkRank {
    public int maximalNetworkRank(int n, int[][] es) {
        int[] degree = new int[n];
        boolean[][] con = new boolean[n][n];
        for (int[] e : es) {
            degree[e[0]]++;
            degree[e[1]]++;
            con[e[0]][e[1]] = true;
            con[e[1]][e[0]] = true;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int cur = degree[i] + degree[j];
                if (con[i][j]) {
                    cur--;
                }
                //    System.out.println(i+","+j+" "+degree[i]+" "+degree[j]);
                max = Math.max(cur, max);
            }
        }
        return max;
    }
}
