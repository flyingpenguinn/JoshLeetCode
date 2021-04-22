import base.ArrayUtils;

import java.util.Arrays;


public class KConcatenateMaxSubarraySum {
    // Note this is not similar to max circular subarray- in that problem we can deal with length ==n at most
    private long mod = 1000000007;
    public int kConcatenationMaxSum(int[] a, int k) {
        int n = a.length;
        long sum = getsum(a);
        long cad = cadane(a);
        long maxleft = max(a, 0, n, 1);
        long maxright = max(a, n-1, -1, -1);
        long maxlr = maxleft + maxright;
        long rt = 0;
        if(sum>=0){
            rt = Math.max(sum*k, Math.max(cad, (k-2)*sum + maxlr));
        }else{
            rt= Math.max(cad, k>=2? maxlr:0);
        }
        return (int)(rt%mod);
    }

    private long getsum(int[] a){
        return Arrays.stream(a).sum();
    }

    private long cadane(int[] a){
        int n = a.length;
        long cur = 0;
        long max = 0; // allowing empty subarray, otherwise = -MAX
        for(int i=0; i<n; i++){
            if(cur<0){
                cur = a[i];
            }else{
                cur += a[i];
            }
            max = Math.max(max, cur);
        }
        return max;
    }

    private long max(int[] a, int l, int r, int d){
        long max = 0;
        long cur = 0;
        for(int i=l; i!= r; i+=d){
            cur += a[i];
            max = Math.max(max, cur);
        }
        return max;
    }

    public static void main(String[] args) {
        //     System.out.println(sol.kConcatenationMaxSum(ArrayUtils.read1d("2,-5,1,0,-2,-2,2"), 2));
        System.out.println(new KConcatenateMaxSubarraySum().kConcatenationMaxSum(ArrayUtils.read1d("[5,-3,5]"), 2));
    }
}
