public class GenerateSchedule {
    // TODO: berger table for scheduling
    public int[][] generateSchedule(int n) {
        if (n <= 4) return new int[0][0];
        int[][][] r = rounds(n);
        int e = n * (n - 1) / 2;

        int[][] seq1 = linGreedy(r, n, -1, -1);
        int[][] seq2 = linGreedy(r, n, seq1[e - 1][0], seq1[e - 1][1]);

        int[][] res = new int[e * 2][2];
        for (int i = 0; i < e; ++i) { res[i][0] = seq1[i][0]; res[i][1] = seq1[i][1]; }
        for (int i = 0; i < e; ++i) { res[e + i][0] = seq2[i][1]; res[e + i][1] = seq2[i][0]; }
        return res;
    }

    private int[][][] rounds(int n) {
        boolean odd = (n & 1) == 1;
        int N = odd ? n + 1 : n, R = odd ? n : n - 1, M = odd ? (n - 1) / 2 : n / 2;
        int[][][] out = new int[R][M][2];
        int[] p = new int[N];
        for (int i = 0; i < N; ++i) p[i] = i;
        int d = odd ? n : -1;
        for (int t = 0; t < R; ++t) {
            int w = 0;
            for (int i = 0; i < N / 2; ++i) {
                int x = p[i], y = p[N - 1 - i];
                if (x == d || y == d) continue;
                if (x < y) { out[t][w][0] = x; out[t][w][1] = y; }
                else { out[t][w][0] = y; out[t][w][1] = x; }
                ++w;
            }
            int last = p[N - 1];
            for (int i = N - 1; i >= 2; --i) p[i] = p[i - 1];
            p[1] = last;
        }
        return out;
    }

    private int[][] linGreedy(int[][][] r, int n, int la, int lb) {
        int R = r.length, M = r[0].length, e = n * (n - 1) / 2;
        boolean[][] used = new boolean[R][M];
        int[][] seq = new int[e][2];
        int idx = 0;

        while (idx < e) {
            boolean found = false;
            for (int t = 0; t < R && !found; ++t) {
                for (int k = 0; k < M && !found; ++k) {
                    if (used[t][k]) continue;
                    int x = r[t][k][0], y = r[t][k][1];
                    if (la == -1 || ((x != la && x != lb) && (y != la && y != lb))) {
                        seq[idx][0] = x; seq[idx][1] = y;
                        used[t][k] = true;
                        la = x; lb = y;
                        ++idx;
                        found = true;
                    }
                }
            }
            if (!found) return new int[0][0];
        }
        return seq;
    }
}
