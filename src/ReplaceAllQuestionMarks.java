public class ReplaceAllQuestionMarks {
    public String modifyString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char before = i == 0 ? ')' : sb.charAt(i - 1);
            char after = i == n - 1 ? ')' : s.charAt(i + 1);
            if (s.charAt(i) != '?') {
                sb.append(s.charAt(i));
            } else {
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j != before && j != after) {
                        sb.append(j);
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }
}
