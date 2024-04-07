public class LexicographicallySmallestStringAfterOperationWithCons {
    public String getSmallestString(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            int diffa = Math.min(cind, 26 - cind);
            if (diffa <= k) {
                k -= diffa;
                sb.append("a");
            } else {
                int ncind = cind - k;
                sb.append((char) ('a' + ncind));
                k = 0;
            }
        }
        return sb.toString();
    }
}
