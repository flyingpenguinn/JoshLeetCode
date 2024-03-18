public class CountSubstringsStartEndGivenChar {
    // or just (freq + 1)*freq/ 2
    public long countSubstrings(String s, char c) {
        int n = s.length();
        long res = 0;
        long seen = 0;
        for (int i = 0; i < n; ++i) {
            char cur = s.charAt(i);
            if (cur == c) {
                ++seen;
                res += seen;
            }
        }
        return res;
    }
}
