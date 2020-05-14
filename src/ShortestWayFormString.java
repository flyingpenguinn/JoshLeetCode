public class ShortestWayFormString {
    public int shortestWay(String s, String t) {
        int rt = 1 + dos(s, 0, t, 0);
        return rt <= 0 ? -1 : rt;
    }

    int dos(String s, int si, String t, int ti) {
        int sn = s.length();
        int tn = t.length();
        if (ti == tn) {
            return 0;
        }


        for (int i = si; i < sn; i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                return dos(s, i + 1, t, ti + 1);
            }
        }
        for (int i = 0; i < si; i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                return 1 + dos(s, i + 1, t, ti + 1);
            }
        }
        return Integer.MIN_VALUE;
    }
}
