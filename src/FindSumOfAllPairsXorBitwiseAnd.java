public class FindSumOfAllPairsXorBitwiseAnd {
    // think if xor as +, and as *
    // a1&B1 ^ a2&b2 = a1+a2  * b1+b2
    public int getXORSum(int[] a, int[] b) {
        int xor1 = 0;
        for (int i = 0; i < a.length; i++) {
            xor1 ^= a[i];
        }
        int xor2 = 0;
        for (int j = 0; j < b.length; j++) {
            xor2 ^= b[j];
        }
        return xor1 & xor2;
    }
}

class FindSumOfAllPairsXorAnotherWay {
    // for a given bit i, it's 1 in ca[i]*cb[i] times
    // it's 1 in the end of the xor calculation if the above count is odd
    public int getXORSum(int[] a, int[] b) {
        int[] ca = count(a);
        int[] cb = count(b);
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((ca[i] * cb[i]) % 2 == 1) {
                res |= (1 << i);
            }
        }
        return res;
    }

    private int[] count(int[] a) {
        int[] ca = new int[32];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < 32; j++) {
                if (((a[i] >> j) & 1) == 1) {
                    ca[j]++;
                }
            }
        }
        return ca;
    }
}
