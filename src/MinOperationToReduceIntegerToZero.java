public class MinOperationToReduceIntegerToZero {
    public int minOperations(int n) {
        //    System.out.println(Integer.toBinaryString(n));
        int way1 = Integer.bitCount(n);
        if (way1 == 1) {
            return 1;
        }
        for (int j = 0; j < 32; ++j) {
            if (((n >> j) & 1) == 1) {
                return Math.min(way1, Math.min(1 + minOperations(n + (1 << j)), 1 + minOperations(n - (1 << j))));
            }
        }
        return way1;

    }
}
