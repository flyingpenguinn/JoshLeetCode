import java.util.ArrayList;
import java.util.List;

public class FindSequenceOfStringsAppearOnScreen {
    public List<String> stringSequence(String t) {
        int n = t.length();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            String sub = t.substring(0, i + 1);
            char[] subc = sub.toCharArray();
            int sublen = subc.length;
            char last = subc[sublen - 1];
            for (char j = 'a'; j <= last; ++j) {
                subc[sublen - 1] = j;
                res.add(new String(subc));
            }
        }
        return res;
    }
}
