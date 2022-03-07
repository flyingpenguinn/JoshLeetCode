import java.util.Arrays;

public class AppendKIntegersWithMinimumSum {
    public long minimalKSum(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += a[i];
        }
        long start = 1;
        long res = 0;
        while (k > 0 && start < a[0]) {
            res += start;
            ++start;
            --k;
        }
        for (int i = 1; i < n && k > 0; ++i) {
            start = a[i - 1] + 1;
            while (start < a[i] && k > 0) {
                res += start;
                ++start;
                --k;
            }
        }
        start = a[n - 1] + 1;
        long end = start + k - 1;
        res += (start + end) * k / 2;
        return res;
    }
}
