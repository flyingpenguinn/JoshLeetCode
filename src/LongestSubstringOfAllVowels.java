public class LongestSubstringOfAllVowels {
    // when it's bad we start from either current i or i+1. ditch all things before i
    private String str = "aeiou";

    public int longestBeautifulSubstring(String s) {
        int n = s.length();
        int start = 0;
        int res = 0;
        int nextj = 0;
        int i = 0;
        while (i < n) {
            int j = i;
            if (s.charAt(i) == str.charAt(nextj)) {
                while (j < n && s.charAt(j) == str.charAt(nextj)) {
                    j++;
                }
                if (nextj == str.length() - 1) {
                    res = Math.max(res, j - start);
                    nextj = 0; // if good also start a new one
                } else {
                    nextj++;
                }
                i = j;
            } else {
                // if invalid start a new one
                nextj = 0;
                start = s.charAt(i) == 'a' ? i : i + 1;
                i++;
            }
        }
        return res;
    }
}
