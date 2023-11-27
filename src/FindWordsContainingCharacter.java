import java.util.ArrayList;
import java.util.List;

public class FindWordsContainingCharacter {
    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            String w = words[i];
            if (w.indexOf(x) != -1) {
                res.add(i);
            }
        }
        return res;
    }
}
