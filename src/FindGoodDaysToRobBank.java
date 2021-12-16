import java.util.ArrayList;
import java.util.List;

public class FindGoodDaysToRobBank {
    public List<Integer> goodDaysToRobBank(int[] a, int t) {
        int n = a.length;
        int[] left = new int[n];
        int lst = 0;
        int i = 1;
        while (i < n) {
            while (i < n && a[i] <= a[i - 1]) {
                left[i] = lst;
                ++i;
            }
            if (i < n) {
                left[i] = i;
                lst = i++;
            }
        }
        int[] right = new int[n];
        right[n - 1] = n - 1;
        i = n - 2;
        int rst = n - 1;
        while (i >= 0) {
            while (i >= 0 && a[i] <= a[i + 1]) {
                right[i] = rst;
                --i;
            }
            if (i >= 0) {
                right[i] = i;
                rst = i--;
            }
        }
        List<Integer> res = new ArrayList<>();
        int ri = 0;
        for (i = 0; i < n; ++i) {
            if (i - left[i] >= t && right[i] - i >= t) {
                res.add(i);
            }
        }
        return res;
    }
}
