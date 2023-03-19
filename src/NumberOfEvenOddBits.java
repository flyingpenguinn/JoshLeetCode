public class NumberOfEvenOddBits {
    public int[] evenOddBit(int n) {
        int bit = 0;
        int[] res = new int[2];
        while (n > 0) {
            int cur = (n & 1);
            if (cur == 1 && bit % 2 == 0) {
                ++res[0];
            } else if (cur == 1 && bit % 2 == 1) {
                ++res[1];
            }
            n = (n >> 1);
            ++bit;
        }
        return res;
    }
}
