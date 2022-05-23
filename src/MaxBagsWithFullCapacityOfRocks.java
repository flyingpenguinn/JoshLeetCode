import java.util.Arrays;

public class MaxBagsWithFullCapacityOfRocks {
    public int maximumBags(int[] capacity, int[] rocks, int ar) {
        int n = capacity.length;
        int[] rem = new int[n];
        int full = 0;
        for (int i = 0; i < n; ++i) {
            rem[i] = capacity[i] - rocks[i];
            if (rem[i] == 0) {
                ++full;
            }
        }
        Arrays.sort(rem);
        int i = 0;
        while (i < n && rem[i] == 0) {
            ++i;
        }
        for (; i < n && ar > 0; ++i) {
            int put = Math.min(ar, rem[i]);
            ar -= put;
            rem[i] -= put;
            if (rem[i] == 0) {
                ++full;
            }
        }
        return full;
    }
}
