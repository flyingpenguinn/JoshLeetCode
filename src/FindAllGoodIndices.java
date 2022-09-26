import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllGoodIndices {
    public List<Integer> goodIndices(int[] a, int k) {
        int n = a.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < n; ++i) {
            if (a[i] <= a[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (a[i] <= a[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = k; i < n - k; ++i) {
            if (left[i - 1] >= k && right[i + 1] >= k) {
                res.add(i);
            }
        }
        return res;
    }
}
