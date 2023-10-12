import java.util.List;

public class ApplyOperationsToMaximizeSquareSum {
    // bits falling down
    private long Mod = (long) (1e9 + 7);
    private int base = 32;

    public int maxSum(List<Integer> a, int k) {
        int n = a.size();
        int[][] bits = new int[n][base];
        for (int i = 0; i < n; ++i) {
            int ai = a.get(i);
            for (int j = 0; j < base; ++j) {
                bits[i][j] = ((ai >> j) & 1);
            }
        }
        // System.out.println(Arrays.deepToString(bits));
        for (int j = 0; j < base; ++j) {
            int pi = 0;
            for (int i = 0; i < n; ++i) {
                if (bits[i][j] == 1) {
                    bits[pi++][j] = 1;
                }
            }
            while (pi < n) {
                bits[pi++][j] = 0;
            }
        }

        //   System.out.println(Arrays.deepToString(bits));
        long res = 0;
        for (int i = 0; i < k; ++i) {
            //   System.out.println(Arrays.toString(bits[i]));
            long v = 0;
            for (int j = 0; j < base; ++j) {
                if (bits[i][j] == 1) {
                    v |= (1 << j);
                }
            }
            res += 1L * v * v;
            res %= Mod;
        }
        return (int) res;
    }
}
