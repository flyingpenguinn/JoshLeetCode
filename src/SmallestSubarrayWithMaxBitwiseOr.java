import java.util.Arrays;

public class SmallestSubarrayWithMaxBitwiseOr {
    // LC 2411, redo 2026-11-20: one shot (the 2022 attempt took 4 WA).
    // Max OR starting at i = OR of a[i..n-1]. Each bit only needs to be reached once ("at least one" -> target, not wall):
    // ones[i][b] = nearest index >= i with bit b set; answer = max over bits of ones[i][b] - i + 1 (at least 1).
    // A single rolling array would do, since res[i] only needs ones[i].
    public int[] smallestSubarrays(int[] a) {
        int n = a.length;
        int limit = 32;
        int[] res = new int[n];
        int[][] ones = new int[n+1][limit];
        Arrays.fill(ones[n], -1);
        for(int i=n-1; i>=0; --i){
            ones[i] = Arrays.copyOf(ones[i+1], limit);
            int v = a[i];
            for(int j=0; j<limit; ++j){
                if(((v>>j)&1)==1){
                    ones[i][j] = i;
                }
            }
        }
        Arrays.fill(res, 1);
        for(int i=0; i<n; ++i){
            int clen = 1;
            for(int j=limit-1; j>=0; --j){
                if(((ones[i][j]!= -1))){
                    clen = Math.max(clen, ones[i][j]-i+1);
                }
            }
            res[i] = clen;
        }
        return res;
    }
}
