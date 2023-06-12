import java.util.Arrays;

public class NeitherMinNorMax {
    // can also take middle of the top 3
    public int findNonMinOrMax(int[] a) {
        int n = a.length;
        if(n<3){
            return -1;
        }
        Arrays.sort(a);
        return a[1];
    }
}
