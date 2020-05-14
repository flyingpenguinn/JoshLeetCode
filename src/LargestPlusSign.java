public class LargestPlusSign {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        // use 0 for 1, -1 for 0
        int[][] g = new int[n][n];
        // updown can share
        int[][] updown = new int[n][n];
        for (int[] m : mines) {
            g[m[0]][m[1]] = -1;
        }
        // up and left
        for (int i = 0; i < n; i++) {
            int left = 0;
            for (int j = 0; j < n; j++) {
                if (g[i][j] >= 0) {
                    int upd = (i == 0 ? 0 : updown[i - 1][j]) + 1;
                    int leftd = left + 1;
                    updown[i][j] = upd;
                    left = leftd;
                    g[i][j] = Math.min(upd, leftd);
                    //System.out.println(i+","+j+" "+upd+" "+leftd);

                } else {
                    updown[i][j] = 0;
                    left = 0;
                }
            }
        }
        //System.out.println("----");

        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            int right = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (g[i][j] >= 0) {
                    int downd = (i == n - 1 ? 0 : updown[i + 1][j]) + 1;
                    int rightd = right + 1;
                    updown[i][j] = downd;
                    right = rightd;
                    int min = Math.min(g[i][j], Math.min(updown[i][j], right));
                    //  System.out.println(i+","+j+" "+downd+" "+rightd);

                    max = Math.max(min, max);
                } else {
                    updown[i][j] = 0;
                    right = 0;
                }
            }
        }
        return max;
    }
}
