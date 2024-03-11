import java.util.Arrays;

public class AppleRedistributionToBoxes {
    public int minimumBoxes(int[] a, int[] c) {
        int sum = 0;
        for (int ai : a) {
            sum += ai;
        }
        Arrays.sort(c);
        int m = c.length;
        int res = 0;
        for (int i = m - 1; i >= 0 && sum > 0; --i) {
            ++res;
            sum -= c[i];
        }
        return res;
    }
}
