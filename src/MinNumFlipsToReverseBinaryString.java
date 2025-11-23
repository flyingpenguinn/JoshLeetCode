public class MinNumFlipsToReverseBinaryString {
    public int minimumFlips(int n) {
        String sn = Integer.toBinaryString(n);
        String rsn = new StringBuilder(sn).reverse().toString();
        int len = sn.length();
        int res = 0;
        for (int i = 0; i < len; ++i) {
            if (sn.charAt(i) != rsn.charAt(i)) {
                ++res;
            }
        }
        return res;
    }
}
