import java.util.Arrays;

public class MinSpaceWasteToPackage {
    // the normal two pointer is n2 when a is full length but b is consisted of len =1
    // note the above solution has complexity sigma(an*bi) = an*bn
    // we need to accelerate walking on a hence thought of jumping around
    private long mod = 1000_000_007L;
    private long Max = 1000_000_000_000_000_000L;

    public int minWastedSpace(int[] a, int[][] boxes) {
        long res = Max;
        Arrays.sort(a);
        long[] sum = new long[a.length];
        for(int i=0; i<a.length; i++){
            sum[i] = (i==0?0:sum[i-1])+a[i];
        }
        for( int[] box: boxes){
            long cur = waste(a, box, sum);
            res = Math.min(cur, res);
        }
        return res>=Max?-1: (int)(res%mod);
    }

    private long waste(int[] a, int[] box, long[] sum){
        int n = a.length;
        Arrays.sort(box);
        int i = 0;
        long res = 0;
        double bn = box.length;
        for(int j = 0; j< bn && i<n; j++){
            // i... cut all <=boxes[j]
            while(j< bn && box[j]<a[i]){
                j++;
            }
            if(j== bn){
                return Max;
            }
            int cut = lastSmallerEqual(a, i, n-1, box[j]);
            long as =sum[cut]- (i==0?0:sum[i-1]);
            long items = cut-i+1;
            res += items*box[j]-as;
            i = cut+1;
        }
        return i<n? Max: res;
    }

    private int lastSmallerEqual(int[] a, int l, int u, int t){
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid]<=t){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return u;
    }
}
