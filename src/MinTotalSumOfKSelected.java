import java.util.Arrays;

public class MinTotalSumOfKSelected {
    public long maxSum(int[] a, int k, int mul) {
        int n = a.length;
        long res = 0;
        Arrays.sort(a);
        for (int i = n - 1; i >= 0 && k > 0; --i) {
            long v = a[i];
            //   System.out.println(v+" "+mul);
            if (mul > 1) {

                res += v * mul;
                --mul;
            } else {
                res += v;
            }
            --k;
        }
        return res;
    }
}
