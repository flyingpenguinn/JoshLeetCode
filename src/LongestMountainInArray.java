import java.util.Arrays;

public class LongestMountainInArray {

    // enumerate the peak of mountain then find its right end, minus the left end
    public int longestMountain(int[] a) {
        if (a.length <= 2) {
            return 0;
        }
        int n = a.length;
        int max = 0;
        int curstart = 0;
        int i = 1;
        while (i < a.length) {
            boolean isPeak = a[i] > a[i - 1] && i + 1 < n && a[i] > a[i + 1];
            if (isPeak) {
                while (i + 1 < n && a[i] > a[i + 1]) {
                    i++;
                }
                max = Math.max(max, i - curstart + 1);
            }
            boolean isStart = i + 1 < n && a[i] < a[i + 1] && a[i] <= a[i - 1];
            if (isStart) {
                curstart = i;
            }
            i++;
        }
        return max;
    }
}

class MountainInArrayNaive {
    // record the left and right boundary for each element i then check if they are peak and how big
    public int longestMountain(int[] a) {
        int n = a.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1]) {
                left[i] = left[i - 1] == -1 ? i - 1 : left[i - 1];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] > a[i + 1]) {
                right[i] = right[i + 1] == -1 ? i + 1 : right[i + 1];
            }
        }
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] != -1 && right[i] != -1) {
                int cur = right[i] - left[i] + 1;
                r = Math.max(r, cur);
            }
        }
        return r;
    }
}