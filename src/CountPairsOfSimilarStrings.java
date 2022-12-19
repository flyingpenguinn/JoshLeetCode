import java.util.HashSet;
import java.util.Set;

public class CountPairsOfSimilarStrings {
    public int similarPairs(String[] words) {
        int n = words.length;
        int res = 0;
        Set<Character>[] sets = new HashSet[n];
        for (int i = 0; i < n; ++i) {
            Set<Character> set1 = new HashSet<>();
            for (char c1 : words[i].toCharArray()) {
                set1.add(c1);
            }
            sets[i] = set1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (sets[i].equals(sets[j])) {
                    ++res;
                }
            }
        }
        return res;
    }
}
