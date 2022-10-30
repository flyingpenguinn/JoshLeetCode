import java.util.ArrayList;
import java.util.List;

public class WordWithinTwoEditsOfDict {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String q : queries) {
            for (String d : dictionary) {
                if (diff(q, d) <= 2) {
                    res.add(q);
                    break;
                }
            }
        }
        return res;
    }

    private int diff(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                ++res;
            }
            if (res > 2) {
                break;
            }
        }
        return res;
    }
}
