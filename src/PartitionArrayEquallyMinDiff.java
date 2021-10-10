import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class PartitionArrayEquallyMinDiff {
    public int minimumDifference(int[] a) {
        int n = a.length;
        int hn = n/2;
        long[] left = new long[hn];
        long sum = 0;
        for(int i=0; i<hn; ++i){
            left[i] = a[i];
            sum += a[i];
        }
        long[] right = new long[hn];
        for(int i=hn; i<n; ++i){
            right[i-hn] = a[i];
            sum += a[i];
        }
        TreeSet<Long>[] lsum = new TreeSet[hn+1];
        for(int i=0; i<=hn; ++i){
            lsum[i] = new TreeSet<>();
        }
        populate(left, lsum);
        TreeSet<Long>[] rsum = new TreeSet[hn+1];
        for(int i=0; i<=hn; ++i){
            rsum[i] = new TreeSet<>();
        }
        populate(right, rsum);
        long res = Long.MAX_VALUE;
        for(int lsize = 0; lsize<=n/2; ++lsize){
            int rsize = hn-lsize;
            for(long va: lsum[lsize]){
                // minimize abs(sum-2*va-2*vb)
                // pick in rsum that is closest to the target
                long target = (sum-2*va)/2;
                // first >= and last </ note this is the way to get a number so that abs(sum) is closest to 0
                Long[] cres = new Long[]{rsum[rsize].ceiling(target), rsum[rsize].floor(target)};
                for(Long cre: cres) {
                    if (cre != null) {
                        long cur = Math.abs(sum - 2 * va - 2 * cre);
                        res = Math.min(res, cur);
                    }
                }
            }
        }
        return (int) res;
    }


    private void populate(long[] a, TreeSet<Long>[] sum){
        int n = a.length; // half the original length
        for(int st = 0; st<(1<<n); ++st){
            int size = Integer.bitCount(st);
            long csum = 0;
            for(int i=0; i<n; ++i){
                if(((st>>i)&1)==1){
                   csum += a[i];
                }
            }
            sum[size].add(csum);
        }
    }

    public static void main(String[] args) {
        System.out.println(new PartitionArrayEquallyMinDiff().minimumDifference(ArrayUtils.read1d("2,-1,0,4,-2,-9")));
    }
}
