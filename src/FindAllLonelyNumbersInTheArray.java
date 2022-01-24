import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllLonelyNumbersInTheArray {
    public List<Integer> findLonely(int[] a) {
        int n = a.length;
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        for (int ai : a) {
            if (m.get(ai) == 1 && !m.containsKey(ai - 1) && !m.containsKey(ai + 1)) {
                res.add(ai);
            }
        }
        return res;
    }
}
