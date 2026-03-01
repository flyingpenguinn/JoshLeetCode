public class TrimTrailingVowels {
    String vow = "aeiou";

    public String trimTrailingVowels(String s) {
        int n = s.length();
        int i = n - 1;
        while (i >= 0) {
            if (vow.indexOf(s.charAt(i)) != -1) {
                --i;
            } else {
                break;
            }
        }
        return s.substring(0, i + 1);
    }
}
