import java.util.Arrays;

public class MaxHappinessSum {
    public long maximumHappinessSum(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        long res = 0;
        int selected = 0;
        for (int i = n - 1; i >= 0 && k > 0; --i) {
            long cur = Math.max(a[i] - selected, 0);
            if (cur == 0) {
                break;
            }
            res += cur;
            ++selected;
            --k;
        }
        return res;
    }
}
