import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumOfDistances {
    public long[] distance(int[] a) {
        int n = a.length;
        Map<Integer, List<Long>> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.computeIfAbsent(a[i], p -> new ArrayList<>()).add(Long.valueOf(i));
        }
        long[] res = new long[n];
        for (int k : m.keySet()) {
            List<Long> l1 = m.get(k);
            long allcount = l1.size();
            long allsum = 0;
            for (int i = 0; i < l1.size(); ++i) {
                allsum += l1.get(i);
            }
            long left = 0;
            for (int i = 0; i < l1.size(); ++i) {
                long index = l1.get(i);
                left += index;
                long right = allsum - left;
                long count = i + 1;
                long cur1 = count * index - left;
                long cur2 = right - (allcount - count) * index;
                res[(int) index] = cur1 + cur2;
            }
        }
        return res;
    }
}
