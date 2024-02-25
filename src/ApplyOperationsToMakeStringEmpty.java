public class ApplyOperationsToMakeStringEmpty {
    public String lastNonEmptyString(String s) {
        int[] cnt = new int[26];
        int max = 0;
        for (char c : s.toCharArray()) {
            int cind = c - 'a';
            ++cnt[cind];
            max = Math.max(max, cnt[cind]);
        }
        StringBuilder sb = new StringBuilder();
        boolean[] seen = new boolean[26];
        for (int i = s.length() - 1; i >= 0; --i) {
            char c = s.charAt(i);
            int cind = c - 'a';
            if (cnt[cind] == max && !seen[cind]) {
                sb.append(c);
                seen[cind] = true;
            }
        }
        sb.reverse();
        return sb.toString();
    }
}
