public class VowelGamesInString {
    public boolean doesAliceWin(String s) {
        String vow = "aeiou";
        int n = s.length();
        int vs = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (vow.indexOf(c) != -1) {
                ++vs;
            }
        }
        if (vs == 0) {
            return false;
        } else {
            return true;
        }
    }
}
