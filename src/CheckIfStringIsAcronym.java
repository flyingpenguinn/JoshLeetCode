import java.util.List;

public class CheckIfStringIsAcronym {
    public boolean isAcronym(List<String> words, String s) {
        StringBuilder sb = new StringBuilder();
        int n = words.size();
        for (int i = 0; i < n; ++i) {
            sb.append(words.get(i).charAt(0));
        }
        return sb.toString().equals(s);
    }
}
