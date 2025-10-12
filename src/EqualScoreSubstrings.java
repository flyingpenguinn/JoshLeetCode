public class EqualScoreSubstrings {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a' + 1;
            sum += cind;
        }
        int left = 0;
        for (int i = 0; i < n-1; ++i) {
            int cind = s.charAt(i) - 'a' + 1;
            left += cind;
            if (left == sum - left) {
                return true;
            }
        }
        return false;
    }
}
