import java.util.Arrays;

public class LongestMountainInArray {
    public int longestMountain(int[] a) {
        int i = 0;
        int n = a.length;
        int max = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && a[j] > a[j - 1]) {
                j++;
            }
            if (j == i + 1) {
                i = j;
                continue;
            }
            // j-1 is the likely peak
            int k = j;
            while (k < n && a[k] < a[k - 1]) {
                k++;
            }
            if (k == j) {
                i = k;
                continue;
            }
            max = Math.max(max, k - i);
            i = k - 1; // k-1 is end of last segment. note the end of last one can be start of this one, so can't be i=k
        }
        return max;
    }
}
