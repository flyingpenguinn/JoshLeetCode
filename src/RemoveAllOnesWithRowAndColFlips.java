import java.util.Arrays;

public class RemoveAllOnesWithRowAndColFlips {
    // every one is either the same or opposite of row 0
    public boolean removeOnes(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for(int i=1; i<m; ++i){
            if(!opposite(a[i], a[0]) && !Arrays.equals(a[i], a[0])){
                return false;
            }
        }
        return true;
    }

    private boolean opposite(int[] a, int[] b){
        int n = a.length;
        for(int i=0; i<n; ++i){
            if(a[i] == b[i]){
                return false;
            }
        }
        return true;
    }
}
