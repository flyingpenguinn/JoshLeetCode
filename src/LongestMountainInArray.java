import java.util.Arrays;

public class LongestMountainInArray {
    public int longestMountain(int[] a) {
        int n = a.length;
        int i = 0;
        int res = 0;
        // enumerate starts
        while (i + 2 < n) {
            // i is either 0 or some bottom of a mountain
            if (a[i] >= a[i + 1]) {
                i++;
            } else {
                // a[i] < a[i+1]
                int j = i;
                while (j + 1 < n && a[j] < a[j + 1]) {
                    j++;
                }
                int peak = j;
                // a[j]>=a[j+1]
                while (j + 1 < n && a[j] > a[j + 1]) {
                    j++;
                }
                // a[j]<=a[j+1] or j==n-1. mountain ending at j but we must check if it's bigger than peak
                if (j > peak) {
                    int cur = j - i + 1;
                    res = Math.max(res, cur);
                }
                i = j; // j is end of this segment and start of the next segmeng
            }
        }
        return res;
    }
}
