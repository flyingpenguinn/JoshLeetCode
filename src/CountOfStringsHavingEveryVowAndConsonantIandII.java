public class CountOfStringsHavingEveryVowAndConsonantIandII {
    // two pointers!
    public int countOfSubstrings(String w, int k) {
        int n = w.length();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; ++j) {
                sb.append(w.charAt(j));
                if (good(sb.toString(), k)) {
                    ++res;
                }
            }
        }
        return res;
    }

    private String vow = "aeiou";

    private boolean good(String s, int k) {
        int n = s.length();
        int cons = 0;
        int[] vm = new int[vow.length()];
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int vi = vow.indexOf(c);
            if (vi == -1) {
                ++cons;
            } else {
                ++vm[vi];
            }
        }
        for (int i = 0; i < vow.length(); ++i) {
            if (vm[i] == 0) {
                return false;
            }
        }
        return cons == k;
    }
}
