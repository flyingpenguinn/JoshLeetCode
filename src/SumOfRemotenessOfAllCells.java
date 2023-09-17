import java.util.Arrays;

public class SumOfRemotenessOfAllCells {
    private int[][] dirs = {{-1, 0}, {0, -1}};
    private int[] pa;
    private long[] score;
    private int n;

    public long sumRemoteness(int[][] a) {
        n = a.length;
        pa = new int[n * n];
        Arrays.fill(pa, -1);
        score = new long[n * n];
        int n = a.length;
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == -1) {
                    continue;
                }

                sum += a[i][j];
                int code = code(i, j);
                score[code] = a[i][j];
                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];
                    int ncode = code(ni, nj);
                    if (ni >= 0 && ni < n && nj >= 0 && nj < n && a[ni][nj] != -1) {
                        union(code, ncode);
                    }
                }
            }
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == -1) {
                    continue;
                }
                int code = code(i, j);
                int root = find(code);
                //  System.out.println(code+" has root "+root+" has score "+score[root]+" has sum "+sum);
                long cscore = score[root];
                long cur = sum - cscore;
                res += cur;
            }
        }
        return res;
    }

    private int find(int i) {
        if (pa[i] == -1) {
            return i;
        }
        int root = find(pa[i]);
        pa[i] = root;
        return root;
    }

    private void union(int i, int j) {
        int ri = find(i);
        int rj = find(j);
        if (ri == rj) {
            return;
        }
        pa[ri] = rj;

        score[rj] += score[ri];
    }

    private int code(int i, int j) {
        return i * n + j;
    }
}
