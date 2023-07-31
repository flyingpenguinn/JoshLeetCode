import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShortestStringThatContainsThreeStrings {
    public int countCompleteSubarrays(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            set.add(ai);
        }
        int size = set.size();
        return subarraysWithKDistinct(a, size);
    }

    private int subarraysWithKDistinct(int[] a, int k) {
        return atmost(a, k) - atmost(a, k - 1);
    }

    private int atmost(int[] a, int k) {
        if (k == 0) {
            return 0;
        }
        int n = a.length;
        int start = 0;
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
            while (m.size() > k) {
                m.put(a[start], m.get(a[start]) - 1);
                if (m.get(a[start]) == 0) {
                    m.remove(a[start]);
                }
                start++;
            }
            // start...i good, have at most k chars
            res += i - start + 1;
        }
        return res;
    }
}
