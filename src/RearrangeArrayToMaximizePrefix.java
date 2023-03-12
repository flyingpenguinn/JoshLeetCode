import java.util.Arrays;

public class RearrangeArrayToMaximizePrefix {
    public int maxScore(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int res = 0;
        long sum = 0;
        int i = n - 1;
        for (; i >= 0; --i) {
            sum += a[i];
            if (sum > 0) {
                ++res;
            }
            if (sum < 0) {
                break;
            }
        }
        return res;
    }
}
