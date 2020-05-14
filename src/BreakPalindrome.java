public class BreakPalindrome {
    public String breakPalindrome(String p) {
        if (p.length() <= 1) {
            return "";
        }
        char[] ps = p.toCharArray();
        int i = 0;
        int j = ps.length - 1;
        while (i < j) {
            if (ps[i] != 'a') {
                ps[i] = 'a';
                return new String(ps);
            }
            i++;
            j--;
        }
        // aaa=>aab
        ps[ps.length - 1] = 'b';
        return new String(ps);
    }
}
