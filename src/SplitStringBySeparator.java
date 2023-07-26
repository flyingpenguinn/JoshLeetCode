import java.util.ArrayList;
import java.util.List;

public class SplitStringBySeparator {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> res = new ArrayList<>();
        for (String w : words) {
            String[] ss = w.split(String.valueOf("\\" + separator));
            for (String s : ss) {
                if (!s.isEmpty()) {
                    res.add(s);
                }
            }
        }
        return res;
    }
}
