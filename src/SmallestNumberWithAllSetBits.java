public class SmallestNumberWithAllSetBits {
    public int smallestNumber(int n) {
        for (int i = 0; i < 32; ++i) {
            int p = (1 << i) - 1;
            if (p >= n) {
                return p;
            }
        }
        return -1;
    }
}
