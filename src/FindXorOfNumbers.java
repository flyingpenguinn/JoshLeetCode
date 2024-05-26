import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindXorOfNumbers {
    public int duplicateNumbersXOR(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int k : m.keySet()) {
            if (m.get(k) == 2) {
                list.add(k);
            }
        }

        int xor = 0;
        for (int i = 0; i < list.size(); ++i) {
            xor ^= list.get(i);
        }
        return xor;
    }
}
