public class RemoveDigitFromNumberToMaximize {
    public String removeDigit(String s, char digit) {
        int n = s.length();
        int lasti = -1;
        for (int i = 0; i + 1 < n; ++i) {
            if (s.charAt(i) != digit) {
                continue;
            }
            lasti = i;
            char nc = s.charAt(i + 1);
            if (nc > digit) {
                return s.substring(0, i) + s.substring(i + 1);
            }
        }
        lasti = s.charAt(n - 1) == digit ? n - 1 : lasti;
        return s.substring(0, lasti) + s.substring(lasti + 1);
    }
}
