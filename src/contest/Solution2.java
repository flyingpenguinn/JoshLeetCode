package contest;

import base.ArrayUtils;

import java.util.*;

public class Solution2 {

    private int brute(int[] a, int k){
        int n = a.length;
        Set<Integer> sets = new HashSet<>();
        for(int i=0; i<n; ++i){
            int cur = a[i];
            sets.add(cur);
            for(int j=i+1; j<n; ++j){
                cur &= a[j];
                sets.add(cur);
            }
        }
        int res = (int) (1e9+10);
        for(int si: sets){
            res = Math.min(res, Math.abs(k-si));
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(new Solution2().brute(new int[]{3,9,4,2,6,8},10));
    }
}
