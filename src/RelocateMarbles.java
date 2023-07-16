import java.util.*;

public class RelocateMarbles {
    public List<Integer> relocateMarbles(int[] a, int[] moveFrom, int[] moveTo) {
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            set.add(ai);
        }
        int n = moveFrom.length;
        for (int i = 0; i < n; ++i) {
            int from = moveFrom[i];
            int to = moveTo[i];
            set.remove(from);
            set.add(to);
        }
        List<Integer> res = new ArrayList<>();
        for (int k : set) {
            res.add(k);
        }
        Collections.sort(res);
        return res;
    }
}
