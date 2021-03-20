import java.util.Arrays;

public class MaxNumConsecutiveNumsYouCanMake {
    // if we get a set with 0...i, then by i+1 we either break it or add to the set of numbers we can make
    public int getMaximumConsecutive(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int next = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] > next) {
                break;
            }
            next += a[i];
        }
        return next;
    }
}
