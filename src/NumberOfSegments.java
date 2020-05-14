public class NumberOfSegments {
    public int countSegments(String s) {
        int l = 0;
        int u = s.length() - 1;
        while (l <= u && s.charAt(l) == ' ') {
            l++;
        }
        while (l <= u && s.charAt(u) == ' ') {
            u--;
        }
        if (l > u) {
            return 0;
        }
        int seg = 1;
        for (int i = l; i <= u; i++) {
            if (s.charAt(i) == ' ' && s.charAt(i - 1) != ' ') {
                seg++;
            }
        }
        return seg;
    }
}
