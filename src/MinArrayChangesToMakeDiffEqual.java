import java.util.*;

public class MinArrayChangesToMakeDiffEqual {
    // x can not be too big.
    // for a pair, a,b and a<b, we can make a=0, then x must <=b
    // we can also make b = k, then x must <=k-a
    // so each pair has a "comfortable x" threshold. smaller than this value we need two operations
    // for each possible diff value, binary search how many are smaller
    public int minChanges(int[] a, int k) {
        int n = a.length;
        Map<Integer, Integer> diff = new HashMap<>();
        int[] vs = new int[n/2];
        int vi = 0;
        for (int i = 0; i < n / 2; ++i) {
            int cur = Math.abs(a[i] - a[n - 1 - i]);
            diff.put(cur, diff.getOrDefault(cur, 0)+1);
            int minv = Math.min(a[i], a[n-1-i]);
            int maxv = Math.max(a[i], a[n-1-i]);
            int v = Math.max(k-minv, maxv);
            vs[vi++] = v;
        }
        Arrays.sort(vs);
        //  System.out.println(diff);
        // System.out.println(Arrays.toString(vs));
        int res = 2*n;
        for(int d: diff.keySet()){
            int pos = binaryLastSmaller(vs, d);
            //   System.out.println(d+" "+pos);
            int zeros = diff.get(d);
            int twos = pos;
            int ones = vs.length - twos;
            ones -= zeros;
            int cur = ones + 2*twos;
            //  System.out.println(d+" "+ones+" "+twos+" "+zeros);
            res = Math.min(res, cur);
        }
        return res;
    }

    private int binaryLastSmaller(int[] a, int t){
        int l = 0;
        int u = a.length-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid] >=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }

}
