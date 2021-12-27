import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntervalsBetweenIdenticalElements {
    // the result for a given element = sum of bigger - bigger count*a[i]+ smaller count*a[i]+sum of smaller
    // use prefix sum and two pointer: we increase the "pos" index as we go
    public long[] getDistances(int[] ia) {        
        int n = ia.length;
        Long[] a = new Long[n];
        for(int i=0; i<n; ++i){
            a[i] = Long.valueOf(ia[i]);
        }
        Map<Long, List<Integer>> lm1 = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            lm1.computeIfAbsent(a[i], k -> new ArrayList<>()).add(i);
        }
        Map<Long, List<Long>> lm2 = new HashMap<>();
        for (long k : lm1.keySet()) {
            List<Long> list2 = new ArrayList<>();
            List<Integer> list1 = lm1.get(k);
            list2.add(Long.valueOf(list1.get(0)));
            for (int i = 1; i < list1.size(); ++i) {
                list2.add(list2.get(i - 1) + list1.get(i));
            }
            lm2.put(k, list2);
        }
        Map<Long, Integer> im = new HashMap<>();
        long[] res = new long[n];
        for (int i = 0; i < n; ++i) {
            List<Integer> list1 = lm1.get(a[i]);
            int pos = im.getOrDefault(a[i], 0);
            int ln = list1.size();
            List<Long> list2 = lm2.get(a[i]);
            long part1 = list2.get(ln - 1) - (pos == 0 ? 0 : list2.get(pos - 1));
            long part2 = (pos == 0 ? 0 : list2.get(pos - 1));
            long p1n = ln - pos;
            long p2n = pos;
            res[i] = part1 - part2 + (p2n - p1n) * i;
            im.put(a[i], pos + 1);
        }
        return res;
    }
}
