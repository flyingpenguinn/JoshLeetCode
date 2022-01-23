import java.util.Arrays;

public class MinCostOfBuyingCandiesWithDiscount {
    public int minimumCost(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int res = 0;
        int i = n - 1;
        for (; i >= 2; i -= 3) {
            res += a[i];
            res += a[i - 1];
        }
        while (i >= 0) {
            res += a[i];
            --i;
        }
        return res;
    }
}
