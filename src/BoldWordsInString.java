import java.util.Arrays;

public class BoldWordsInString {
    public String boldWords(String[] words, String s) {
        // match longer words first
        Arrays.sort(words, (a, b) -> Integer.compare(b.length(), a.length()));
        StringBuilder sb = new StringBuilder();
        int n = s.length();

        int bn = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i + words[j].length() <= bn) {
                    break;
                }
                if (s.startsWith(words[j], i)) {
                    if (bn == -1) {
                        sb.append("<b>");
                    }
                    bn = Math.max(bn, i + words[j].length());
                    break;
                }
            }
            if (i == bn) {
                sb.append("</b>");
                bn = -1;
            }
            sb.append(s.charAt(i));
        }
        if (n == bn) {
            sb.append("</b>");
        }
        return sb.toString();
    }
}

// setup marks and then reap the start/end of it
class BoldWordsAlternative {
    public String boldWords(String[] words, String s) {
        int n = s.length();
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.length; j++) {
                if (s.startsWith(words[j], i)) {
                    for (int k = i; k < i + words[j].length(); k++) {
                        marked[k] = true;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (marked[i] && (i == 0 || !marked[i - 1])) {
                sb.append("<b>");
            }
            sb.append(s.charAt(i));
            if (marked[i] && (i == n - 1 || !marked[i + 1])) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
}
