import java.util.Arrays;

public class MaxOperationsToExceedThresholdI {
    public int minOperations(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        int steps = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] < k) {
                ++steps;
            }
        }
        return steps;
    }
}
