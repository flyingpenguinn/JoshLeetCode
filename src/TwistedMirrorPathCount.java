import java.util.HashMap;
import java.util.Map;

public class TwistedMirrorPathCount {
    private int code(int i, int j, int n) {
        return i * n + j;
    }

    Map<Integer, Integer> nextr = new HashMap<>();
    Map<Integer, Integer> nextd = new HashMap<>();

    public int uniquePaths(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (a[i][j] == 0) {
                    continue;
                }
                populatenextrd(i, j, a);
            }
        }

        long[][] dp = new long[m][n];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (a[i][j] == 1) {
                    continue;
                }
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = 1;
                } else if (i == m - 1) {
                    int nrj = j + 1;
                    if (a[i][nrj] == 1) {

                    } else {
                        dp[i][j] += dp[i][nrj];
                    }
                } else if (j == n - 1) {
                    int ndi = i + 1;
                    if (a[ndi][j] == 1) {
                    } else {
                        dp[i][j] += dp[ndi][j];
                    }
                } else {
                    int rj = j + 1;
                    int nexti = i;
                    int nextj = rj;
                    if (a[i][rj] == 1) {
                        Integer cnext = nextr.get(code(i, rj, n));
                        if (cnext == null) {
                            nexti = -1;
                        } else {
                            nexti = cnext / n;
                            nextj = cnext % n;
                        }

                    }
                    if (nexti != -1) {
                        dp[i][j] += dp[nexti][nextj];
                    }
                    int di = i + 1;
                    nexti = di;
                    nextj = j;
                    if (a[di][j] == 1) {
                        Integer cnext = nextd.get(code(di, j, n));
                        if (cnext == null) {
                            nexti = -1;
                        } else {
                            nexti = cnext / n;
                            nextj = cnext % n;
                        }

                    }
                    if (nexti != -1) {
                        dp[i][j] += dp[nexti][nextj];
                    }
                }
                dp[i][j] %= (mod);
            }
        }
        return (int) dp[0][0];
    }

    private long mod = (long) (1e9 + 7);

    private void populatenextrd(int i, int j, int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int cij = code(i, j, n);
        if (i + 1 < m) {
            int cijnext = code(i + 1, j, n);
            if (a[i + 1][j] == 0) {
                nextr.put(cij, cijnext);
            } else {
                Integer cached = nextd.get(cijnext);
                nextr.put(cij, cached);
            }
        }
        if (j + 1 < n) {
            int cijnext = code(i, j + 1, n);
            if (a[i][j + 1] == 0) {
                nextd.put(cij, cijnext);
            } else {
                Integer cached = nextr.get(cijnext);
                nextd.put(cij, cached);
            }
        }
    }
}
