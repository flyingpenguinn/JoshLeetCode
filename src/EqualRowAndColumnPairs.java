import java.util.Arrays;

public class EqualRowAndColumnPairs {
    public int equalPairs(int[][] a) {
        int n = a.length;
        int res = 0;
        for(int j=0; j<n; ++j){
            int[] cur = new int[n];
            for(int k=0; k<n; ++k){
                cur[k] = a[k][j];
            }
            for(int i=0; i<n; ++i){
                if(Arrays.equals(cur, a[i])){
                    ++res;
                }
            }
        }
        return res;
    }
}
