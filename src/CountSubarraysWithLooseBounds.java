public class CountSubarraysWithLooseBounds {
    public long countSubarrays(int[] a, int minK, int maxK) {
        int n = a.length;
        int i = 0;
        long res = 0;
        while(i<n){
            if(a[i]<minK || a[i]>maxK){
                ++i;
                continue;
            }
            int j = i;
            while(j<n && a[j]>=minK && a[j]<=maxK){
                ++j;
            }
            long len = j-i;
            long count = len*(len+1)/2;
            res += count;
            i = j;
        }
        return res;
    }
}
