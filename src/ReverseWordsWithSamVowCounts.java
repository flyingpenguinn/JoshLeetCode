public class ReverseWordsWithSamVowCounts {
    private String vow = "aeoiu";

    public String reverseWords(String s) {
        String[] ss = s.split(" ");
        int vows0 = count(ss[0]);
        StringBuilder res = new StringBuilder();
        res.append(ss[0]);
        int n = ss.length;
        for (int i = 1; i < n; ++i) {
            String cur = ss[i];
            int cc = count(ss[i]);
            if (cc == vows0) {
                StringBuilder rev = new StringBuilder(cur).reverse();
                res.append(" ");
                res.append(rev.toString());
            } else {
                res.append(" ");
                res.append(cur);
            }
        }
        return res.toString();
    }

    private int count(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (vow.indexOf(c) != -1) {
                ++res;
            }
        }
        return res;
    }
}
