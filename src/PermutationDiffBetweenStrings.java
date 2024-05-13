public class PermutationDiffBetweenStrings {
    public int findPermutationDifference(String s, String t) {
        int[] sm = new int[26];
        int[] tm = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            sm[cind] = i;
        }
        for (int i = 0; i < n; ++i) {
            int cind = t.charAt(i) - 'a';
            tm[cind] = i;
        }
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            int cur = Math.abs(sm[i] - tm[i]);
            res += cur;
        }
        return res;
    }
}
