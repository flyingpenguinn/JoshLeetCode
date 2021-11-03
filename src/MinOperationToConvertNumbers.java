import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinOperationToConvertNumbers {


    public int minimumOperations(int[] a, int start, int goal) {
        Deque<long[]> q = new ArrayDeque<>();
        q.offerLast(new long[]{start, 0});
        int n = a.length;
        Set<Long> seen = new HashSet<>();
        seen.add((long)start);
        while(!q.isEmpty()){
            long[] top = q.pollFirst();
            long tv = top[0];
            long tst = top[1];
            if(tv==goal){
                return (int) tst;
            }
            if(tv<0 || tv>1000){
                continue;
            }
            for(int i=0; i<n; ++i){
                long[] nvs = new long[3];
                nvs[0] = (tv+a[i]);
                nvs[1] = (tv-a[i]);
                nvs[2] = (tv^a[i]);
                for(long nv: nvs){
                    if((nv<0 || nv>1000) && nv!= goal){
                        continue;
                    }
                    if(!seen.contains(nv)){
                        seen.add(nv);
                        q.offerLast(new long[]{nv, tst+1});
                    }
                }
            }
        }
        return -1;
    }

}
