public class CountSubstringsWithoutRepeatingChars {
    public int numberOfSpecialSubstrings(String s) {
        int n = s.length();
        int j = 0;
        int[] count = new int[26];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int sc = s.charAt(i) - 'a';
            ++count[sc];
            while (count[sc] > 1) {
                --count[s.charAt(j) - 'a'];
                ++j;
            }
            // System.out.println(i+"..."+j);
            // j....i good
            int len = i - j + 1;
            res += len;
        }
        return res;
    }
}
