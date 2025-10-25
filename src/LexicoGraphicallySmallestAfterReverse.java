public class LexicoGraphicallySmallestAfterReverse {
    public String lexSmallest(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        String res = s;
        for (int i = 0; i < n; ++i) {
            String cut = s.substring(0, i + 1);
            String rem = s.substring(i + 1);
            String cur1 = new StringBuilder(cut).reverse().toString() + rem;
            if (cur1.compareTo(res) < 0) {
                res = cur1;
            }
            String cur2 = cut + new StringBuilder(rem).reverse().toString();
            if (cur2.compareTo(res) < 0) {
                res = cur2;
            }
        }
        return res;
    }
}
