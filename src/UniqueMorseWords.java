import java.util.HashSet;
import java.util.Set;

public class UniqueMorseWords {
    String[] mapping = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    Set<String> transformed = new HashSet<>();

    public int uniqueMorseRepresentations(String[] words) {
        for (String w : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < w.length(); i++) {
                sb.append(mapping[w.charAt(i) - 'a']);
            }
            transformed.add(sb.toString());
        }
        return transformed.size();
    }
}
