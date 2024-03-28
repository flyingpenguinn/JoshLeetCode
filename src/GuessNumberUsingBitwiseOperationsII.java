public class GuessNumberUsingBitwiseOperationsII {
    public int findNumber() {
        int res = 0;
        for (int i = 0; i < 30; ++i) {
            int v = (1 << i);
            if (commonBits(v) > commonBits(v)) {
                res |= (1 << i);
            }
        }
        return res;
    }

    private int commonBits(int num) {
        return -1; // unknown implementation
    }
}
