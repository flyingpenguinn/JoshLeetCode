import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxCoinsFromKConsecutiveBags {

    private long ans = 0;
    private int K;
    private List<long[]> coins;

    public long maximumCoins(int[][] vec, int K) {
        this.K = K;

        // Convert the input to a list of long[] to prevent overflow
        coins = new ArrayList<>();
        for (int[] coin : vec) {
            coins.add(new long[]{coin[0], coin[1], coin[2]});
        }

        // Process intervals starting at each segment's left endpoint
        calculateMaximumCoins();

        // Reverse the intervals to process windows ending at each segment's right endpoint
        reverseIntervals();
        calculateMaximumCoins();

        return ans;
    }

    // Calculate total coins in the interval at index i
    private long calc(int i) {
        long[] coin = coins.get(i);
        return coin[2] * (coin[1] - coin[0] + 1);
    }

    // Main logic for sliding window to calculate maximum coins
    private void calculateMaximumCoins() {
        coins.sort(Comparator.comparingLong(a -> a[0])); // Sort intervals by start point
        long currentSum = 0;

        int n = coins.size();
        for (int i = 0, j = 0; i < n; i++) {
            // Move the right pointer to find the first non-overlapping interval
            while (j < n && coins.get(j)[0] < coins.get(i)[0] + K) {
                currentSum += calc(j);
                j++;
            }

            // Deduct the uncovered part of the last interval in the current window
            long det = Math.max(0L, coins.get(j - 1)[1] - (coins.get(i)[0] + K) + 1);
            ans = Math.max(ans, currentSum - det * coins.get(j - 1)[2]);

            // Slide the window by removing the current interval
            currentSum -= calc(i);
        }
    }

    // Reverse intervals to process windows ending at each segment's right endpoint
    private void reverseIntervals() {
        for (int i = 0; i < coins.size(); i++) {
            long[] coin = coins.get(i);
            long l = 1_000_000_000L - coin[1];
            long r = 1_000_000_000L - coin[0];
            coin[0] = l;
            coin[1] = r;
        }
    }

}
