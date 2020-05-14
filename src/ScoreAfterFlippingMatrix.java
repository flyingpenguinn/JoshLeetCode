public class ScoreAfterFlippingMatrix {

    // flip row if made bigger,flip col if better for over half
    public int matrixScore(int[][] a) {
        prows(a);
        pcols(a);
        return sc(a);
    }

    void prows(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] == 0) {
                flip(a, i, true);
            }
        }
    }

    void pcols(int[][] a) {
        for (int j = 0; j < a[0].length; j++) {
            int onecount = onecount(a, j);
            if (a.length - onecount > onecount) {
                flip(a, j, false);
            }
        }
    }

    void flip(int[][] a, int k, boolean row) {
        if (row) {
            for (int j = 0; j < a[0].length; j++) {
                a[k][j] = a[k][j] == 1 ? 0 : 1;
            }
        } else {
            for (int i = 0; i < a.length; i++) {
                a[i][k] = a[i][k] == 1 ? 0 : 1;
            }
        }
    }

    int sc(int[][] a, int row) {
        int sc = 0;
        for (int j = 0; j < a[0].length; j++) {
            sc = (sc << 1) + a[row][j];
        }
        return sc;
    }

    int onecount(int[][] a, int col) {
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i][col] == 1) {
                c++;
            }
        }
        return c;
    }

    int sc(int[][] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += sc(a, i);
        }
        return sum;
    }
}
