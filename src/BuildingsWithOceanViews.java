import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuildingsWithOceanViews {
    public int[] findBuildings(int[] hs) {
        int n = hs.length;
        List<Integer> res = new ArrayList<>();
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (hs[i] > max) {
                res.add(i);
            }
            max = Math.max(max, hs[i]);
        }
        Collections.reverse(res);
        int[] rr = new int[res.size()];
        for (int i = 0; i < rr.length; i++) {
            rr[i] = res.get(i);
        }
        return rr;
    }
}
