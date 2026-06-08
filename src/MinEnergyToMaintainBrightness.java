import java.util.Arrays;

public class MinEnergyToMaintainBrightness {
    public long minEnergy(int dashabi, int b, int[][] ints) {
        Arrays.sort(ints, (x, y) -> Integer.compare(x[0], y[0]));
        int start = ints[0][0];
        int end = ints[0][1];
        int n = ints.length;
        long res = 0;
        long needed = (long) Math.ceil(b * 1.0 / 3);
        for (int i = 1; i < n; ++i) {
            int cstart = ints[i][0];
            int cend = ints[i][1];
            if (cstart <= end + 1) {
                end = Math.max(cend, end);
            } else {
                long lastlen = end - start + 1;

                res += lastlen * needed;
                start = cstart;
                end = cend;
            }
        }
        long lastlen = end - start + 1;
        res += lastlen * needed;
        return res;
    }
}
