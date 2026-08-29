import java.util.Arrays;

public class MinimumTotalPriceAfterApplyingDiscounts {
    public double minPrice(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        Arrays.sort(a);
        Arrays.sort(b);
        int i = an-1;
        int j = bn-1;
        double res = 0;
        while(i>=0 && j>=0){
            double cur = 1.0*a[i]*(100-b[j])/100;
            res += cur;
            --i;
            --j;
        }
        while(i>=0){
            res += a[i];
            --i;
        }
        return res;
    }
}
