import java.util.Arrays;
import java.util.TreeMap;

public class PutBoxesIntoWareHouse2 {
    public int maxBoxesInWarehouse(int[] b, int[] w) {
        // heavy ones on the two sides, light ones in the middle
        Arrays.sort(b);
        int j=0;
        int res = 0;
        // same as warehouse 1
        int i = b.length-1;
        for(; i>=0 && j<w.length; i--){
            if(b[i]<= w[j]){
                b[i] = Integer.MAX_VALUE;
                j++;
                res++;
            }
        }
        int limit = j;
        // from right to left warehouse 1
        i = b.length-1;
        j = w.length-1;
        for(; i>=0 && j>=limit; i--){
            if(b[i]<= w[j]){
                j--;
                res++;
            }
        }
        return res;
    }
}
