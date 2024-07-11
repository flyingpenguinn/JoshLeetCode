public class CountTripletsWithEvenXorSetBitsII {
    // long version
    public long tripletCount(int[] a, int[] b, int[] c) {
        long[][] m = new long[3][2];
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
        long way1 = m[0][0] * m[1][0] * m[2][0];
        long way2 = m[0][0] * m[1][1] * m[2][1];
        long way3 = m[0][1] * m[1][0] * m[2][1];
        long way4 = m[0][1] * m[1][1] * m[2][0];
        long res = way1 + way2 + way3 + way4;
        return res;
    }
}
