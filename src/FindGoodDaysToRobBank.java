import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindGoodDaysToRobBank {
    public List<Integer> goodDaysToRobBank(int[] a, int k) {
        int n = a.length;
        int[] left = new int[n];
        int[] right = new int[n+1];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        right[n] = 0;
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
        for (int i = 0; i < n; ++i) {
            if (left[i] >=k+1 && right[i] >= k+1) {
                res.add(i);
            }
        }
        return res;
    }
}
