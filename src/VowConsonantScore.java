public class VowConsonantScore {
    String vows = "aeiou";

    public int vowelConsonantScore(String s) {
        int n = s.length();
        int cv = 0;
        int vv = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (vows.indexOf(c) != -1) {
                ++vv;
            } else if (Character.isAlphabetic(c)) {
                ++cv;
            }
        }
        if (cv > 0) {
            return vv / cv;
        } else {
            return 0;
        }
    }
}
