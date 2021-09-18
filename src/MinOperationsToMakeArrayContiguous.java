import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinOperationsToMakeArrayContiguous {
    public int minOperations(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        List<Integer> li = new ArrayList<>();
        li.add(a[0]);
        for(int i=1; i<n; ++i){
            if(a[i]!= a[i-1]){
                li.add(a[i]);
            }
        }
        int res = n+1;
        // assuming each number is used, calculate the rest
        for(int i=0; i<li.size(); ++i){
            int low = li.get(i);
            int high = low+n-1;
            int inplace = range(li, low, high);
            int cur = n-inplace;
            res = Math.min(res, cur);
        }
        return res;
    }

    // last <=
    private int range(List<Integer> a, int low, int high){
        int highindex = binarylast(a, high);
        int lowindex = binaryfirst(a, low);
        return highindex-lowindex+1;
    }

    // last <=
    private int binaryfirst(List<Integer> a, int t){
        int l = 0;
        int u = a.size()-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a.get(mid)>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }


    // last <=
    private int binarylast(List<Integer> a, int t){
        int l = 0;
        int u = a.size()-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a.get(mid)<=t){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return u;
    }
}
