import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeMap;

public class PutBoxesIntoWarehouse {
    // smaller box would block large box so we find the biggest fitting box for a given warehouse from the big to small box
    public int maxBoxesInWarehouse(int[] b, int[] w) {
        Arrays.sort(b);
        int i = b.length-1;
        int j = 0;
        int res = 0;
        while(i>=0 && j<w.length){
            if(b[i]<=w[j]){
                res++;
                j++;
            }
            i--;;
        }
        return res;
    }
}