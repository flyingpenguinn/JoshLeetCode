public class ValidPalindromeIV {
    public boolean makePalindrome(String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;
        char[] c = s.toCharArray();
        int changed = 0;
        while (i < j) {
            if (c[i] != c[j]) {
                ++changed;
                if (changed > 2) {
                    return false;
                }
            }
            ++i;
            --j;
        }
        return true;
    }
}
