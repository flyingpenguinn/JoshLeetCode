import java.util.Arrays;

public class MinDiffBetweenHigestAndLowestScoreInK {
    public int minimumDifference(int[] a, int k) {
        Arrays.sort(a);
        int res = Integer.MAX_VALUE;
        for(int i=k-1; i<a.length; ++i){
            int head = i-k+1;
            int cur = a[i]-a[head];
            res = Math.min(cur, res);
        }
        return res;
    }
}
