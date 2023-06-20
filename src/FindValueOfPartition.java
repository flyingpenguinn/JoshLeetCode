import java.util.Arrays;

public class FindValueOfPartition {
    public int findValueOfPartition(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; ++i) {
            int diff = a[i] - a[i - 1];
            res = Math.min(res, diff);
        }
        return res;
    }
}
