import java.util.HashMap;
import java.util.Map;

public class FindUniqueEvenElement {
    public int firstUniqueEven(int[] a) {
        int n = a.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int ai : a) {
            cnt.put(ai, cnt.getOrDefault(ai, 0) + 1);
        }
        for (int i = 0; i < n; ++i) {
            if (a[i] % 2 == 0 && cnt.get(a[i]) == 1) {
                return a[i];
            }
        }
        return -1;
    }
}
