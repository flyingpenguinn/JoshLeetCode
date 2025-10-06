import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MaxDistBetweenUnequalWordsInArrayIAndII {
    public int maxDistance(String[] words) {
        int n = words.length;
        int res = 0;
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            if (!m.containsKey(words[i])) {
                m.put(words[i], i);
            }
        }
        TreeSet<Integer> firsts = new TreeSet<>();
        for (String k : m.keySet()) {
            firsts.add(m.get(k));
            if (firsts.size() > 2) {
                firsts.pollLast();
            }
        }
        if (firsts.size() == 1) {
            return 0;
        }
        for (int i = 0; i < n; ++i) {
            int cfirst = m.get(words[i]);
            boolean back = false;
            if (firsts.contains(cfirst)) {
                back = true;
                firsts.remove(cfirst);
            }
            int pre = firsts.first();
            int cur = i - pre + 1;
            res = Math.max(res, cur);
            if (back) {
                firsts.add(cfirst);
            }
        }
        return res;
    }
}
