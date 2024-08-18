public class CountSubarraySatisfyKConstraintI {
    public int countKConstraintSubstrings(String s, int k) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int c0 = 0;
            int c1 = 0;
            for (int j = i; j < n; ++j) {
                int cind = s.charAt(j) - '0';
                if (cind == 0) {
                    ++c0;
                } else {
                    ++c1;
                }
                if (c0 <= k || c1 <= k) {
                    ++res;
                }
            }
        }
        return res;
    }
}
