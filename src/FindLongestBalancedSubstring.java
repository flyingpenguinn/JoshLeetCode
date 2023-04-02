public class FindLongestBalancedSubstring {
    public int findTheLongestBalancedSubstring(String s) {
        int n = s.length();
        int c0 = 0;
        int c1 = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '0') {
                if (c0 > 0 && c1 > 0) {
                    res = Math.max(res, 2 * Math.min(c0, c1));
                    c0 = 1;
                } else {
                    ++c0;
                }
                c1 = 0;
            } else {
                ++c1;
            }
        }
        res = Math.max(res, 2 * Math.min(c0, c1));
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FindLongestBalancedSubstring().findTheLongestBalancedSubstring("01000111"));
    }
}
