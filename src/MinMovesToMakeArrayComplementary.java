import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class MinMovesToMakeArrayComplementary {

    // min+lim always >= max+1, so between min+1, max+lim it's one move. zero moves basically will be counted as one move, so deduct them
    public int minMoves(int[] a, int lim) {
        int n = a.length;
        int i = 0;
        int j = n-1;
        int[] seg = new int[200000+100];
        Map<Integer,Integer> cnt = new HashMap<>();
        while(i<j){
            int min = Math.min(a[i], a[j]);
            int max = Math.max(a[i], a[j]);
            seg[2] += 2;
            seg[min+1] -= 1;
            seg[max+lim+1] += 1;
            int sum = min+max;
            cnt.put(sum, cnt.getOrDefault(sum,0)+1);
            ++i;
            --j;
        }
        int rsum = 0;
        int res = 2*n+1;
        for(i=2; i<seg.length; ++i){
            rsum += seg[i];
            int cur = rsum - cnt.getOrDefault(i, 0);
            res = Math.min(res, cur);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MinMovesToMakeArrayComplementary().minMoves(ArrayUtils.read1d("[3,1,1,1,2,3,2,3,1,3,2,1]"), 3));
    }

}
