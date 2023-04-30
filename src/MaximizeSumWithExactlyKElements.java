import java.util.Arrays;

public class MaximizeSumWithExactlyKElements {
    public int maximizeSum(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        return (a[n - 1] + a[n - 1] + k - 1) * k / 2;
    }
}
