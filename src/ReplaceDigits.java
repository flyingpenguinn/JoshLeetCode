public class ReplaceDigits {
    public String replaceDigits(String s) {
        int n = s.length();
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 1) {
                res[i] = shift(s.charAt(i - 1), s.charAt(i) - '0');
            } else {
                res[i] = s.charAt(i);
            }
        }
        return new String(res);
    }

    private char shift(char c, int delta) {
        int cind = c - 'a' + delta;
        int mod = cind % 26;
        //   System.out.println(mod);
        return (char) ('a' + mod);
    }
}
