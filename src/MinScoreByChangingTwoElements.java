import java.util.Arrays;

public class MinScoreByChangingTwoElements {
    public int minimizeSum(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        if (n <= 3) {
            return 0;
        }
        int high1 = a[n - 2] - a[1];
        int high2 = a[n - 1] - a[2];
        int high3 = a[n - 3] - a[0];
        return Math.min(Math.min(high1, high2), high3);
    }
}
