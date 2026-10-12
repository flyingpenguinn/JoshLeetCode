public class LongestResilientSubarrayI {
    public int resilientSubarray(int[] a, int k) {
        int n = a.length;
        int i = 0;
        int res = 1;
        while(i<n){
            int j = i;
            int rem = a[i]%k;
            long sum = 0;
            while(j<n && a[j]%k == a[i]%k){
                sum += a[j];
                ++j;
                if(sum%k == rem){
                    int len = j-i;
                    res = Math.max(res, len);        
                }
            }          
            i = j;
        }
        return res;
    }
}
