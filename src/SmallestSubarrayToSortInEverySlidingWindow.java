import java.util.Arrays;

public class SmallestSubarrayToSortInEverySlidingWindow {

    // must be starting with a big number ending at a small number
    public int[] minSubarraySort(int[] a, int k) {
        int n = a.length;
        int[] res = new int[n-k+1];
        int ri = 0;
        for(int i=0; i+k-1<n; ++i){
            //  System.out.println("checking "+i);
            int minright = a[i+k-1];
            int left = -1;
            for(int j=i+k-2; j>=i; --j){
                if(a[j]>minright){
                    //    System.out.println(minright+" smaller than "+a[j]);
                    left = j;
                }
                minright = Math.min(minright, a[j]);
            }
            int maxleft = a[i];
            int right = -1;
            for(int j=i+1; j<i+k; ++j){
                if(a[j]<maxleft){
                    //   System.out.println(maxleft+" bigger  than "+a[j]);
                    right = j;
                }
                maxleft = Math.max(maxleft, a[j]);
            }
            if(right != -1  ){
                res[ri++] = right - left +1;
            }else{
                res[ri++] = 0;
            }
        }
        return res;
    }
}

