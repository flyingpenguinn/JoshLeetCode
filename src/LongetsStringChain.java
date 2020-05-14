import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongetsStringChain {
    Map<String, Integer> dp = new HashMap<>();

    // longest path on a dag
    // note from long -> short is faster in checking if short is in the set, than the other way round
    // we dont need to build  a complicated  s->l map. we can work from l to s backward!
    public int longestStrChain(String[] words) {
        Set<String> set = new HashSet<>();
        for (String w : words) {
            set.add(w);
        }
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            int len = dfs(words[i], set);
            max = Math.max(max, len);
        }
        return max;
    }

    private int dfs(String word, Set<String> set) {
        Integer cached = dp.get(word);
        if (cached != null) {
            return cached;
        }
        int max = 1;
        for (int i = 0; i < word.length(); i++) {
            String removed = word.substring(0, i) + word.substring(i + 1);
            if (set.contains(removed)) {
                max = Math.max(max, 1 + dfs(removed, set));
            }
        }
        dp.put(word, max);
        return max;
    }
}
