import java.util.ArrayList;
import java.util.List;

public class FindIndiciesOfStableMountains {
    public List<Integer> stableMountains(int[] h, int t) {
        List<Integer> r = new ArrayList<>();
        for (int i = 1; i < h.length; i++) {
            if (h[i - 1] > t) {
                r.add(i);
            }
        }
        return r;
    }
}
