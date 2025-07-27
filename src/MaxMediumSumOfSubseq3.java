import java.util.Arrays;

public class MaxMediumSumOfSubseq3 {
    public long maximumMedianSum(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int i = 0;
        int j = n - 1;
        long res = 0;
        while (i < j) {
            res += a[j - 1];
            j -= 2;
            ++i;
        }
        return res;
    }
}
