public class CountSubaraysWithFixedBounds {
    public long countSubarrays(int[] a, int minK, int maxK) {
        int n = a.length;
        int jbad = -1;
        int jmin = -1;
        int jmax = -1;
        long res = 0;
        for(int i=0; i<n; ++i){
            if(a[i]<minK || a[i] > maxK){
                jbad = i;
            }
            if(a[i]==minK){
                jmin = i;
            }
            if(a[i]==maxK){
                jmax = i;
            }
            // no else to cover all equal mink = maxk
            res += Math.max(0, Math.min(jmin, jmax)-jbad);
        }
        return res;
    }
}
