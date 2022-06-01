public class RearrangeCharactersToMakeTarget {
    public int rearrangeCharacters(String s, String t) {
        int[] sm = new int[26];
        int[] tm = new int[26];
        int sn = s.length();
        for (int i = 0; i < sn; ++i) {
            int sind = s.charAt(i) - 'a';
            ++sm[sind];
        }
        int tn = t.length();
        for (int i = 0; i < tn; ++i) {
            int tind = t.charAt(i) - 'a';
            ++tm[tind];
        }
        int res = (int) 1e9;
        for (int i = 0; i < 26; ++i) {
            if (tm[i] == 0) {
                continue;
            }
            res = Math.min(res, sm[i] / tm[i]);
        }
        return res;
    }
}
