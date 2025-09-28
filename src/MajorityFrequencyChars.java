import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MajorityFrequencyChars {
    public String majorityFrequencyGroup(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            int cind = c - 'a';
            ++cnt[cind];
        }
        Map<Integer, Set<Character>> sm = new HashMap<>();
        int maxgroupsize = 0;
        for (char c : s.toCharArray()) {
            int cind = c - 'a';
            int cf = cnt[cind];
            sm.computeIfAbsent(cf, k -> new HashSet<>()).add(c);
            maxgroupsize = Math.max(maxgroupsize, sm.get(cf).size());
        }
        StringBuilder sb = new StringBuilder();
        int maxf = 0;
        for (int k : sm.keySet()) {
            if (sm.get(k).size() == maxgroupsize) {
                if (k > maxf) {
                    maxf = k;
                    sb = new StringBuilder();
                    for (char c : sm.get(k)) {
                        sb.append(c);
                    }
                } else if (k == maxf) {
                    for (char c : sm.get(k)) {
                        sb.append(c);
                    }
                }
            }

        }
        return sb.toString();

    }
}
