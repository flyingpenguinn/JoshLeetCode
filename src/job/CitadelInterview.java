package job;

import contest.Solution;

public class CitadelInterview {
    //q1
    public static int sumNumber(int n, int base) {
        // n>=base, base >0

        int lastdivisibleby = (n / base) * base;
        int items = (lastdivisibleby - base) / base + 1;
        return (base + lastdivisibleby) * items / 2;
    }

    // q2
    public int clusters(int[][] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        // assuming it's all 0 and 1
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    dfs(a, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

    // standing on i, j, expand in dfs
    private void dfs(int[][] a, int i, int j) {
        int m = a.length;
        int n = a[0].length;
        a[i][j] = 2;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = (j + d[1]) % n;
            if (nj < 0) {
                nj += n;
            }
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 1) {
                dfs(a, ni, nj);
            }
        }
    }

    // q3, after removing the dimension of m
    private int solve(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[n][2];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int maxlater = 0;
                int minlater = 0;
                if (i == m - 1 && j == n - 1) {
                    dp[n - 1] = new int[]{a[m - 1][n - 1], a[m - 1][n - 1]};
                    continue;
                }
                if (i == m - 1) {
                    int[] later = dp[j + 1];
                    maxlater = later[0];
                    minlater = later[1];
                } else if (j == n - 1) {
                    int[] later = dp[j];
                    maxlater = later[0];
                    minlater = later[1];
                } else {
                    // i< m-1, j<n-1
                    int[] laterrow = dp[j];
                    int[] latercol = dp[j + 1];
                    maxlater = Math.max(laterrow[0], latercol[0]);
                    minlater = Math.min(laterrow[1], latercol[1]);
                }
                int curmax = Math.max(maxlater * a[i][j], minlater * a[i][j]);
                int curmin = Math.min(maxlater * a[i][j], minlater * a[i][j]);
                dp[j] = new int[]{curmax, curmin};
            }
        }
        return dp[0][0];
    }

    public static void test(int[][] g) {
        System.out.println(new CitadelInterview().clusters(g));
    }

    public static void main(String[] args) {
        System.out.println(sumNumber(1000, 3) + sumNumber(1000, 5) - sumNumber(1000, 15));
        int[][] graph = {{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0}, {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}};
        test(graph);

        int[][] a = {{100, 2, -3}, {-4, -5, 6}};
        System.out.println(new CitadelInterview().solve(a));
    }


}
