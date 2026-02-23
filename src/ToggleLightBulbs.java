import java.util.ArrayList;
import java.util.List;

public class ToggleLightBulbs {
    public List<Integer> toggleLightBulbs(List<Integer> a) {
        int[] st = new int[101];
        for (int ai : a) {
            st[ai] ^= 1;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 100; ++i) {
            if (st[i] == 1) {
                res.add(i);
            }
        }
        return res;
    }
}
