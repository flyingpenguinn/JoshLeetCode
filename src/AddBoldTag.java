public class AddBoldTag {
    public String addBoldTag(String s, String[] dict) {
        boolean[] marked = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < dict.length; j++) {
                if (s.startsWith(dict[j], i)) {
                    for (int k = 0; k < dict[j].length(); k++) {
                        marked[i + k] = true;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (marked[i] && (i == 0 || !marked[i - 1])) {
                sb.append("<b>");
            }
            sb.append(s.charAt(i));
            if (marked[i] && (i == s.length() - 1 || !marked[i + 1])) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
}
