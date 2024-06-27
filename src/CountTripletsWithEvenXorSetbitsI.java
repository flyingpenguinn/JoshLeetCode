public class CountTripletsWithEvenXorSetbitsI {
    /*

    The result of whether a[i]^b[j]^c[k] = 3 = (011) has an even number of set bits is given by
(a[i][0]^b[j][0]^c[k][0]) ^ (a[i][1]^b[j][1]^c[k][1]) ^ (a[i][2]^b[j][2]^c[k][2])
(1 means odd and 0 means even). This is equivalent to
(a[i][0]^a[i][1]^a[i][2]) ^ (b[j][0]^b[j][1]^b[j][2]) ^ (c[k][0]^c[k][1]^c[k][2]) .
     */
    public int tripletCount(int[] a, int[] b, int[] c) {
        int[][] m = new int[3][2];
        for (int ai : a) {
            int xor = 0;
            for (int j = 0; j < 32; ++j) {
                int dig = ((ai >> j) & 1);
                xor ^= dig;
            }
            ++m[0][xor];
        }
        for (int bi : b) {
            int xor = 0;
            for (int j = 0; j < 32; ++j) {
                int dig = ((bi >> j) & 1);
                xor ^= dig;
            }
            ++m[1][xor];
        }
        for (int ci : c) {
            int xor = 0;
            for (int j = 0; j < 32; ++j) {
                int dig = ((ci >> j) & 1);
                xor ^= dig;
            }
            ++m[2][xor];
        }
        int way1 = m[0][0] * m[1][0] * m[2][0];
        int way2 = m[0][0] * m[1][1] * m[2][1];
        int way3 = m[0][1] * m[1][0] * m[2][1];
        int way4 = m[0][1] * m[1][1] * m[2][0];
        int res = way1 + way2 + way3 + way4;
        return res;
    }
}
