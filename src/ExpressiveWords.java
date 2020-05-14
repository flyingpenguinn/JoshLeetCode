public class ExpressiveWords {
    public int expressiveWords(String s, String[] words) {
        int c = 0;
        for (String w : words) {
            if (exp(s, w)) {
                c++;
            }
        }
        return c;
    }

    private boolean exp(String s, String w) {
        int si = 0;
        int wi = 0;

        while (si < s.length() && wi < w.length()) {
            if (s.charAt(si) == w.charAt(wi)) {
                char ch = s.charAt(si);
                int sb = si;
                while (si < s.length() && s.charAt(si) == ch) {
                    si++;
                }
                int wb = wi;
                while (wi < w.length() && w.charAt(wi) == ch) {
                    wi++;
                }
                if (si - sb < wi - wb) {
                    return false;
                } else if (si - sb > wi - wb && si - sb < 3) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return si == s.length() && wi == w.length();
    }
}
