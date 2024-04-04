import java.util.Arrays;
import java.util.TreeMap;

public class PutBoxesIntoWareHouse2 {
    public int maxBoxesInWarehouse(int[] b, int[] w) {
        // like problem I the best is on the two sides
        int bn = b.length;
        int wn = w.length;
        int wi1 = 0;
        int wi2 = wn-1;
        int res = 0;
        Arrays.sort(b);
        for(int bi=bn-1; bi>=0 && wi1 <= wi2; --bi){
            if(w[wi1]>=b[bi]){
                ++res;
                ++wi1;
            }else if(w[wi2]>=b[bi]){
                ++res;
                --wi2;
            }
        }
        return res;
    }
}
