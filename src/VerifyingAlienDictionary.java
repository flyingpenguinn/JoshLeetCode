import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class VerifyingAlienDictionary {

    class AlienStringComparator implements Comparator<String> {
        Map<Character, Integer> map;

        public AlienStringComparator(Map<Character, Integer> map) {
            this.map = map;
        }

        @Override
        public int compare(String o1, String o2) {
            int i = 0;
            while (i < o1.length() && i < o2.length()) {
                char co1 = o1.charAt(i);
                char co2 = o2.charAt(i);
                Integer seq1 = map.get(co1);
                Integer seq2 = map.get(co2);
                if (seq1 == null || seq2 == null) {
                    throw new IllegalArgumentException("unknown character");
                }
                if (seq1 < seq2) {
                    return -1;
                } else if (seq1 > seq2) {
                    return 1;
                }
                i++;
            }
            if (i == o1.length() && i == o2.length()) {
                return 0;
            } else if (i == o1.length()) {
                // o2 longer, so o1 is smaller
                return -1;
            } else {
                return 1;
            }
        }
    }

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            if (map.containsKey(c)) {
                throw new IllegalArgumentException("duplicated order");
            }
            map.put(c, i);
        }
        AlienStringComparator cmp = new AlienStringComparator(map);
        for (int i = 0; i + 1 < words.length; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (cmp.compare(w1, w2) > 0) {  // i.compare to i+1 <=0
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
