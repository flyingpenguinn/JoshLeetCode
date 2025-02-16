public class FindSpecialSubstringsOfLengthK {
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            if (j - i == k) {
                return true;
            }
            i = j;
        }
        return false;
    }
}
