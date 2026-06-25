import base.ArrayUtils;

import java.util.*;

public class CountSubarraysWithMajorityElementsII {
    long[] bit;

    public long countMajoritySubarrays(int[] a, int t) {
        int n = a.length;
        bit = new long[3*n];
        int cnt = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            cnt += (a[i] == t) ? 1 : 0;
            int sum = 2*cnt - i;
            long cur = query(bit, sum+n-1);
            res += cur;
            if(cnt>(i+1)/2){
                ++res;
            }
            update(bit, sum+n);
        }
        return res;
    }


    private long query(long[] bit, int i) {
        long res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    private void update(long[] bit, int i) {
        while (i < bit.length) {
            bit[i]++;
            i += i & (-i);
        }
    }

    public static void main(String[] args) {
        System.out.println(new CountSubarraysWithMajorityElementsII().countMajoritySubarrays(ArrayUtils.read1d("[1,2,2,3]"), 2));
    }
}
