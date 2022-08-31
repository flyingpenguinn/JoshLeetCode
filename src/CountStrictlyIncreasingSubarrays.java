public class CountStrictlyIncreasingSubarrays {
    public long countSubarrays(int[] a) {
        int n = a.length;
        int i = 0;
        long res = 0;
        while(i<n){
            int j = i+1;
            while(j<n && a[j]>a[j-1]){
                ++j;
            }
            // i...j-1
            long len = j-i;
            res += len*(len+1)/2;
            i = j;
        }
        return res;
    }
}
