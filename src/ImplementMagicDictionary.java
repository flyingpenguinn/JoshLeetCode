import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ImplementMagicDictionary {
}


class MagicDictionary {
    // in cases of must "match sth other than myself", we need a map to count
    Map<String, Integer> map = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {

    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (String d : dict) {
            map.put(d, 1); // non repetitive
            for (int i = 0; i < d.length(); i++) {
                StringBuilder sb = new StringBuilder(d);
                sb.setCharAt(i, '-');
                String str = sb.toString();
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.setCharAt(i, '-');
            Integer eq = map.get(sb.toString());
            if (eq == null) {
                continue;
            } else if (eq == 1 && map.containsKey(word)) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }
}
