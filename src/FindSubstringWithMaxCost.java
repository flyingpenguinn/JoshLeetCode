public class FindSubstringWithMaxCost {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int n = s.length();
        int[] a = new int[n];
        int[] score = new int[26];
        for (int i = 0; i < 26; ++i) {
            score[i] = i + 1;
        }
        for (int i = 0; i < chars.length(); ++i) {
            char c = chars.charAt(i);
            int cind = c - 'a';
            int v = vals[i];
            score[cind] = v;
        }
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int cind = c - 'a';
            a[i] = score[cind];
        }
        int csum = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (csum < 0) {
                csum = a[i];
            } else {
                csum += a[i];
            }
            res = Math.max(csum, res);
        }
        return res;
    }
}
