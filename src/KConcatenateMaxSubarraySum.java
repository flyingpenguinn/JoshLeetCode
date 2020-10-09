import base.ArrayUtils;


public class KConcatenateMaxSubarraySum {
    // Note this is not similar to max circular subarray- in that problem we can deal with length ==n at most
    private int mod = 1000000007;
    public int kConcatenationMaxSum(int[] a, int k) {
        long allsum = allsum(a);
        long kadne = kadne(a);
        long maxprefix = maxprefix(a);
        long maxsuffix = maxsuffix(a);
        long max = 0;
        if(allsum>=0){
            long way1 = allsum*k;
            long way2 = (k-2)*allsum + maxprefix+maxsuffix;
            max = Math.max(way1, Math.max(way2, kadne));
        }else{
            long way1 = maxprefix+maxsuffix;
            max = Math.max(way1, kadne);
        }
        return (int)(max%mod);
    }

    private int allsum(int[] a){
        int sum =0;
        for(int i=0; i<a.length;i++){
            sum += a[i];
        }
        return sum;
    }

    private int maxprefix(int[] a){
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<a.length;i++){
            sum += a[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    private int maxsuffix(int[] a){
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=a.length-1; i>=0; i--){
            sum += a[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    private int kadne(int[] a){
        int maxending = 0;
        int max = 0;
        for(int i=0; i<a.length;i++){
            maxending = Math.max(maxending+a[i], a[i]);
            max = Math.max(maxending, max);
        }
        return max;
    }

    public static void main(String[] args) {
        //     System.out.println(sol.kConcatenationMaxSum(ArrayUtils.read1d("2,-5,1,0,-2,-2,2"), 2));
        System.out.println(new KConcatenateMaxSubarraySum().kConcatenationMaxSum(ArrayUtils.read1d("[5,-3,5]"), 2));
    }
}
