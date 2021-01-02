import java.util.Arrays;
import java.util.TreeMap;

public class PutBoxesIntoWareHouse2 {
    // gist is to stuff heavy boxes on the two sides, leaving light ones in the middle
    // for put boxes question 1, we only stuff from p1
    public int maxBoxesInWarehouse(int[] b, int[] w) {
        Arrays.sort(b);
        int p1 = 0;
        int p2 = w.length-1;
        int res = 0;
        for(int i=b.length-1; i>=0 && p1<=p2; i--){
            if(b[i]<=w[p1]){
                p1++;
                res++;
            }else if(b[i]<=w[p2]){
                p2--;
                res++;
            }
        }
        return res;
    }
}
