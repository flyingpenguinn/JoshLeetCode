import java.util.Arrays;

public class MaxProductDifferenceBetweenTwoPairs {
    public int maxProductDifference(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int big = a[n - 1] * a[n - 2];
        int small = a[0] * a[1];
        return big - small;
    }
}
