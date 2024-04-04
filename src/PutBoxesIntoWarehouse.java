import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeMap;

public class PutBoxesIntoWarehouse {
    // the "biggest" warehouse is on the very left. if the current box can't fit this warehouse, then it must be thrown away
    // if current boxes fits, all later boxes will fit as we sorted boxes, and we can move onto next box
    public int maxBoxesInWarehouse(int[] b, int[] w) {
        int wn = w.length;
        int bn = b.length;
        Arrays.sort(b);

        int res = 0;
        int wi = 0;
        for (int bi = bn - 1; bi >= 0 && wi < wn; --bi) {
            if (b[bi] <= w[wi]) {
                ++res;
                ++wi;
            }
            // otherwise should throw this one away
        }
        return res;
    }
}