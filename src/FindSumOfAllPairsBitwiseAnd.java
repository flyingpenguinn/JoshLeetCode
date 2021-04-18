public class FindSumOfAllPairsBitwiseAnd {
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
