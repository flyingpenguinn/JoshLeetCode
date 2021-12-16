import java.util.Arrays;

public class FindSubSeqLengthKWithLargestSum {
    public int[] maxSubsequence(int[] a, int k) {
        int n = a.length;
        int[][] v = new int[n][2];
        for(int i=0; i<n; ++i){
            v[i][0]= a[i];
            v[i][1] = i;
        }
        Arrays.sort(v, (x, y) -> Integer.compare(x[0], y[0]));
        int[][] v2 = new int[k][2];
        for(int i=n-1; i>=n-k; --i){
            v2[n-1-i][0] = v[i][0];
            v2[n-1-i][1] = v[i][1];
        }
        Arrays.sort(v2, (x,y)->Integer.compare(x[1], y[1]));
        int[] res = new int[k];
        int ri = 0;
        for(int i=0; i<k; ++i){
            res[ri++] = v2[i][0];
        }
        return res;
    }
}
