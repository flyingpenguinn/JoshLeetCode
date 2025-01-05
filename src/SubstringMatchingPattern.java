public class SubstringMatchingPattern {
    public boolean hasMatch(String s, String p) {
        int n = s.length();
        int pn = p.length();
        int starpos = p.indexOf('*');
        String p1 = p.substring(0, starpos);
        String p2 = p.substring(starpos + 1);
        int f1 = s.indexOf(p1);
        if (f1 == -1) {
            return false;
        }
        if (p2.isEmpty()) {
            return true;
        }
        int f2 = -1;
        for (int i = n - 1; i >= 0; --i) {
            if (s.startsWith(p2, i)) {
                f2 = i;
                break;
            }
        }
        return f1 + p1.length() <= f2;
    }
}
