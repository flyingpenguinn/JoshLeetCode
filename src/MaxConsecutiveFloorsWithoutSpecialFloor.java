import java.util.Arrays;

public class MaxConsecutiveFloorsWithoutSpecialFloor {
    public int maxConsecutive(int b, int t, int[] a) {
        int n = a.length;
        Arrays.sort(a);

        int v1 = a[0] - b;
        int v2 = t - a[n - 1];
        int res = Math.max(v1, v2);
        for (int i = 1; i < n; ++i) {
            int cur = a[i] - a[i - 1] - 1;
            res = Math.max(res, cur);
        }
        return res;
    }
}
