import java.util.Arrays;

public class MinimizeMaxPairSum {
    public int minPairSum(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int i = 0;
        int j = n-1;
        int res = 0;
        while(i<j){
            res = Math.max(res, a[i]+a[j]);
            i++;
            j--;
        }
        return res;
    }
}
