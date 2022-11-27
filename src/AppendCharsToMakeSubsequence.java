public class AppendCharsToMakeSubsequence {
    public int appendCharacters(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int i = 0;
        int j = 0;
        while (i < tn) {
            char tc = t.charAt(i);
            while (j < sn && s.charAt(j) != tc) {
                ++j;
            }
            if (j == sn) {
                break;
            }
            ++i;
            ++j;
        }
        return tn - i;
    }
}
