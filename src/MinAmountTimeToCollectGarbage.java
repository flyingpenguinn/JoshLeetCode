import java.util.*;

public class MinAmountTimeToCollectGarbage {
    private String trucks = "GMP";
    public  int garbageCollection(String[] a, int[] t) {
        int n = a.length;
        int res = 0;
        int[] psum = new int[n];
        psum[0] = t[0];
        for(int i=1; i<t.length; ++i){
            psum[i] = psum[i-1] + t[i];
        }
        for(char c: trucks.toCharArray()){
            int last = -1;
            for(int i=0; i<n; ++i){
                int cur = count(a[i], c);
                res += cur;
                if(cur>=1){
                    last = i;
                }
            }
            res += last<=0? 0: psum[last-1];
        }
        return res;
    }

    private int count(String s, char c){
        int res = 0;
        for(char si: s.toCharArray()){
            if(si == c){
                ++res;
            }
        }
        return res;
    }
};

