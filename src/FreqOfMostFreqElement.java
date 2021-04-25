import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FreqOfMostFreqElement {
    // sliding window!
    public int maxFrequency(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        int res = 1;
        long sum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            while ((i - start + 1l) * a[i] - sum > k) {
                sum -= a[start++];
            }
            res = Math.max(res,  i - start + 1);
        }
        return res;
    }
}
