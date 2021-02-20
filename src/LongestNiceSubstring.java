public class LongestNiceSubstring {
    // split at the bad character
    public String longestNiceSubstring(String s) {
        return longest(s, 0, s.length() - 1);
    }


    private String longest(String s, int l, int u) {
        boolean[] um = new boolean[26];
        boolean[] lm = new boolean[26];
        for (int i = l; i <= u; i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                um[c - 'A'] = true;
            } else {
                lm[c - 'a'] = true;
            }
        }
        for (int i = l; i <= u; i++) {
            char c = s.charAt(i);
            int cind = Character.isUpperCase(c) ? c - 'A' : c - 'a';
            if (um[cind] && lm[cind]) {
                continue;
            }
            String s1 = longest(s, l, i - 1);
            String s2 = longest(s, i + 1, u);
            return s1.length() >= s2.length() ? s1 : s2;
        }
        return s.substring(l, u + 1);
    }
}
