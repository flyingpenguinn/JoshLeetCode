import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeSet;

public class TwoSumLessThanK {
    public int twoSumLessThanK(int[] a, int k) {
        Arrays.sort(a);
        int i = 0;
        int j = a.length-1;
        int max = -1;
        while(i<j){
            int sum = a[i]+a[j];
            if(sum<k){
                max = Math.max(max, sum);
                i++;
            }else{
                j--;
            }
        }
        return max;
    }
}