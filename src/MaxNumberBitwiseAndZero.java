public class MaxNumberBitwiseAndZero {
    public long maxNumber(long n) {
        for (int i = 63; i >= 0; --i) {
            if (((n >> i) & 1L) == 1L) {
                long base = (1L << i) - 1;
                return base;
            }
        }
        return 0;
    }
}
