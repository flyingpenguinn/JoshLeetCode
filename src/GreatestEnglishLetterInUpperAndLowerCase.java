public class GreatestEnglishLetterInUpperAndLowerCase {
    public String greatestLetter(String s) {
        boolean[] upper = new boolean[26];
        boolean[] lower = new boolean[26];
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upper[c - 'A'] = true;
            } else {
                lower[c - 'a'] = true;
            }
        }
        for (int i = 25; i >= 0; --i) {
            if (upper[i] && lower[i]) {
                return String.valueOf((char) ('A' + i));
            }
        }
        return "";
    }
}
