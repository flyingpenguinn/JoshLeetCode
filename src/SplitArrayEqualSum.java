import base.ArrayUtils;

import java.util.*;

public class SplitArrayEqualSum {
    // set j first, then search i and k separately
    public boolean splitArray(int[] a) {
        if(a==null || a.length==0){
            return false;
        }
        int n = a.length;
        int[] sum = new int[n];
        sum[0] = a[0];
        for(int i=1; i<n;i++){
            sum[i] = sum[i-1]+a[i];
        }
        for(int j=3; j<n; j++){
            Set<Integer> iset = new HashSet<>();
            for(int i=1; i+1<j; i++){
                if(sum[i-1] == sum[j-1] - sum[i] ){
                    iset.add(sum[i-1]);
                }
            }
            for(int k=j+2; k<n-1; k++){
                if(sum[k-1]-sum[j] == sum[n-1]-sum[k]){
                    if(iset.contains(sum[k-1]-sum[j])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}