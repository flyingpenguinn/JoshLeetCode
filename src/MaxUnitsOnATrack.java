import java.util.Arrays;

public class MaxUnitsOnATrack {
    // partial knapsack problem...
    public int maximumUnits(int[][] b, int t) {
        Arrays.sort(b, (x, y) -> Integer.compare(y[1], x[1]));
        // unit value first
        int weight = 0;
        int value = 0;
        for (int i = 0; i < b.length; i++) {
            if (weight + b[i][0] <= t) {
                weight += b[i][0];
                value += b[i][0] * b[i][1];
            } else {
                value += (t - weight) * b[i][1];
                break;
            }
        }
        return value;
    }
}
