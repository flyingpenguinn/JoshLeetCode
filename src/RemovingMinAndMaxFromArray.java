public class RemovingMinAndMaxFromArray {
    public int minimumDeletions(int[] a) {
        int n = a.length;
        int minp = -1;
        int minv = Integer.MAX_VALUE;
        int maxp = -1;
        int maxv = Integer.MIN_VALUE;
        for(int i=0; i<n; ++i){
            if(a[i]<minv){
                minv = a[i];
                minp = i;
            }
            if(a[i]>maxv){
                maxv = a[i];
                maxp = i;
            }
        }
        int minpos = Math.min(minp, maxp);
        int maxpos = Math.max(minp, maxp);
        int way1 = minpos+1+(n-maxpos);
        int way2 = maxpos+1;
        int way3 = n-minpos;
        return Math.min(way1, Math.min(way2, way3));
    }
}
