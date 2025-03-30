public class MinOperationsToMakeArrayElementsZero {
    private static final int z = 16;
    private static final long[] u = new long[z + 1];
    private static final long[] c = new long[z + 1];

    private static boolean initialized = initArrays();

    private static boolean initArrays() {
        u[0] = 1;
        for (int x = 1; x <= z; x++) {
            u[x] = u[x - 1] << 2;
        }
        for (int x = 1; x <= z; x++) {
            long diff = u[x] - u[x - 1];
            long val = x * diff;
            c[x] = c[x - 1] + val;
        }
        return true;
    }

    private long r(long v) {
        if (v <= 0) return 0;
        int i = 0;
        while (i < z && u[i + 1] <= v) i++;
        return c[i] + (v - u[i] + 1) * (i + 1);
    }

    public long minOperations(int[][] arr) {
        long res = 0;
        for (int[] row : arr) {
            long a = row[0];
            long b = row[1];
            long s1 = r(b);
            long s2 = r(a - 1);
            long delta = s1 - s2;
            res += (delta + 1) >> 1;
        }
        return res;
    }
}
