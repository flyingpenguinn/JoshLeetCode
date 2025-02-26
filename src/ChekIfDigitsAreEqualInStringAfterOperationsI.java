public class ChekIfDigitsAreEqualInStringAfterOperationsI {
    public boolean hasSameDigits(String s) {
        while (s.length() > 2) {
            char[] c = s.toCharArray();
            int n = c.length;
            StringBuilder ns = new StringBuilder();
            for (int i = 0; i + 1 < n; ++i) {
                int v1 = c[i] - '0';
                int v2 = c[i + 1] - '0';
                int nv = (v1 + v2) % 10;
                ns.append(nv);
            }
            s = ns.toString();
            //System.out.println(s);
        }
        return s.charAt(0) == s.charAt(1);
    }
}
