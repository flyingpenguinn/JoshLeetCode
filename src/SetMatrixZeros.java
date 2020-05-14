public class SetMatrixZeros {
    //use [i,0] and [0,j] as place holder for the row/col arrays we would have created
    // traps: iterate from 1 as 0 is used for flags and we can't distinguish from =0 because this row ==0 or 1st column = 0
    // check first row and col separatekt
    public void setZeroes(int[][] a) {
        int r0 = 1;
        int c0 = 1;
        int rs = a.length;
        int cs = a[0].length;
        for (int i = 0; i < rs; i++) {
            for (int j = 0; j < cs; j++) {
                if (a[i][j] == 0) {
                    if (i == 0) {
                        r0 = 0;
                    }
                    if (j == 0) {
                        c0 = 0;
                    }
                    a[0][j] = 0;
                    a[i][0] = 0;
                }
            }
        }
        // from 1 avoid mis-setting row0 or col 0
        for (int i = 1; i < rs; i++) {
            for (int j = 1; j < cs; j++) {
                if (a[i][0] == 0) {
                    a[i][j] = 0;
                }
                if (a[0][j] == 0) {
                    a[i][j] = 0;
                }
            }
        }
        if (r0 == 0) {
            for (int j = 0; j < cs; j++) {
                a[0][j] = 0;
            }
        }
        if (c0 == 0) {
            for (int i = 0; i < rs; i++) {
                a[i][0] = 0;
            }
        }
    }
}
