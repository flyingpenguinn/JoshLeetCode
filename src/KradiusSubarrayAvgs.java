import java.util.Arrays;

public class KradiusSubarrayAvgs {
    public int[] getAverages(int[] a, int k) {
        int n = a.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        long sum = 0;
        for(int i=0; i<2*k && i<n; ++i){
            sum += a[i];
        }
        for(int i=2*k; i<n; ++i){
            sum += a[i];
            int cur = (int)(sum/(2*k+1));
            res[i-k] = cur;
            sum -= a[i-2*k];
        }
        return res;
    }
}
