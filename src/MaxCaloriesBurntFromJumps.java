import java.util.Arrays;

public class MaxCaloriesBurntFromJumps {
    public long maxCaloriesBurnt(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        long res = 0;
        int i = 0;
        int j = n - 1;
        long cur = 0;
        while (i <= j) {
            long v1 = (a[i] - cur) * (a[i] - cur);
            long v2 = (a[j] - cur) * (a[j] - cur);
            if (v1 > v2) {
                res += v1;
                cur = a[i];
                ++i;
            } else {
                res += v2;
                cur = a[j];
                --j;
            }
        }
        return res;
    }
}
