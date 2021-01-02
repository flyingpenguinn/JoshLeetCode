import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeMap;

public class PutBoxesIntoWarehouse {
    // basically take big to small boxes and try to stuff them into the warehouse from the left
    public int maxBoxesInWarehouse(int[] bx, int[] wh) {
        int p1 =0;
        int p2 = wh.length-1;
        Arrays.sort(bx);
        int res = 0;
        for(int i = bx.length-1;i>=0 && p1<=p2;i--){
            if(bx[i] <= wh[p1]){
                p1++;
                res++;
            }
        }
        return res;
    }
}