import java.util.Arrays;

public class MinDiffBetweenLargestAndSmallestAfterThreeMoves {
    // remove 0 or 1 or 2 or 3 smallest elements
    public int minDifference(int[] a) {
        int n = a.length;
        if (n <= 4) {
            return 0;
        }
        Arrays.sort(a);
        int min = Integer.MAX_VALUE;
        for (int toRemove = 0; toRemove <= 3; toRemove++) {
            int end = n - 1 - (3 - toRemove);
            int start = toRemove;
            min = Math.min(min, a[end] - a[start]);
        }
        return min;
    }
}
