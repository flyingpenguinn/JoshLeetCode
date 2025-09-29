import java.util.Arrays;

public class NumberOfZigZagArraysI {
    public long splitArray(int[] a) {
        int n = a.length;
        int i = 1;
        long[] left = new long[n];
        Arrays.fill(left, -1);
        left[0] = a[0];
        while(i<n && a[i]>a[i-1]){
            left[i] = left[i-1] + a[i];
            ++i;
        }
        i = n-2;
        long[] right = new long[n];
        Arrays.fill(right, -1);
        right[n-1] = a[n-1];
        while(i>=0 && a[i]>a[i+1]){
            right[i] = right[i+1] + a[i];
            --i;
        }
        long res = Long.MAX_VALUE;
        for(i=0; i<n-1; ++i){
            //  System.out.println("i="+i+" left="+left[i]+" right="+right[i+1]);
            if(left[i] != -1 && right[i+1] != -1){
                long diff = Math.abs(left[i] - right[i+1]);
                res = Math.min(res, diff);
            }
        }
        return res>=Long.MAX_VALUE? -1: res;
    }
}
