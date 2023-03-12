public class CountNumberOfVowelStringsInRange {
    private String vowels = "aeiou";

    public int vowelStrings(String[] words, int left, int right) {
        int res = 0;
        for (int i = left; i <= right; ++i) {
            String w = words[i];
            int wlen = w.length();
            if (isvowel(w.charAt(0)) && isvowel(w.charAt(wlen - 1))) {
                ++res;
            }
        }
        return res;
    }

    private boolean isvowel(char c) {
        return vowels.indexOf(c) != -1;
    }
}
