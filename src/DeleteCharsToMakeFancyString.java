public class DeleteCharsToMakeFancyString {
    public String makeFancyString(String s) {
        int n = s.length();
        int j = 0;
        StringBuilder sb = new StringBuilder();
        char pre = '*';
        int streak = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (pre == c) {
                ++streak;
            } else {
                streak = 1;
            }
            if (streak <= 2) {
                sb.append(s.charAt(i));
            }
            pre = c;
        }
        return sb.toString();
    }
}
