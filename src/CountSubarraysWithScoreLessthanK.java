import base.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class CountSubarraysWithScoreLessthanK {
    public long countSubarrays(int[] a, long k) {
        int n = a.length;
        List<Long> psum = new ArrayList<>();
        long accu = 0;
        long res = 0;
        psum.add(0L);
        for(int i=0; i<n; ++i){
            accu += a[i];
            int pos = binary(psum, accu, k);
            res += psum.size() - pos;
            psum.add(accu);
        }
        return res;
    }

    private int binary(List<Long> psum, long accu, long k){
        int l = 0;
        int u = psum.size()-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            int len = psum.size()-mid;
            long diff = accu - psum.get(mid);
            if(diff*len >= k){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        System.out.println(new CountSubarraysWithScoreLessthanK().countSubarrays(ArrayUtils.read1d("[2,1,4,3,5]"), 10));
    }
}
