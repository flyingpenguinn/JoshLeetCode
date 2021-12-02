import java.util.Arrays;

public class KradiusSubarrayAvgs {
    public int[] getAverages(int[] a, int k) {
        int n = a.length;
        long[] psum = new long[n];
        for(int i=0; i<n; ++i){
            psum[i] = (i==0?0L:psum[i-1])+a[i];
        }
        int[] res = new int[n];
        Arrays.fill(res,-1);
        for(int i=k; i+k<n; ++i){
            long cursum = psum[i+k] -(i==k?0:psum[i-k-1]);
            res[i] = (int) (cursum/(2*k+1));
        }
        return res;
    }
}
