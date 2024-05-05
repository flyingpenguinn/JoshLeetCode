public class ValidWord {
    private String vowel = "aeiouAEIOU";

    public boolean isValid(String s) {
        int n = s.length();
        if (n < 3) {
            return false;
        }
        int conso = 0;
        int vow = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
            if (Character.isAlphabetic(c)) {
                if (vowel.indexOf(c) != -1) {
                    ++vow;
                } else {
                    ++conso;
                }
            }
        }
        return vow >= 1 && conso >= 1;
    }
}
