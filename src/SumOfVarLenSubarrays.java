public class SumOfVarLenSubarrays {
    public int subarraySum(int[] a) {
        int n = a.length;
        int res = 0;
        int[] sum = new int[n];
        sum[0] = a[0];
        for(int i=1; i<n; ++i){
            sum[i] = sum[i-1] + a[i];
        }
        for (int i = 0; i < n; ++i) {
            int start = Math.max(0, i - a[i]);
            res += sum[i] - (start==0?0:sum[start-1]);
        }
        return res;
    }
}
