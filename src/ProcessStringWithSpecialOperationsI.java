public class ProcessStringWithSpecialOperationsI {
    public String processStr(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            }
            if (c == '*') {
                if (sb.length()>0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            if (c == '#') {
                sb.append(sb);
            }
            if (c == '%') {
                sb.reverse();
            }
        }
        return sb.toString();
    }
}
