public class CheckBalancedString {
    public boolean isBalanced(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int odd = 0;
        int even = 0;
        for (int i = 0; i < n; ++i) {
            int cind = c[i] - '0';
            if (i % 2 == 0) {
                even += cind;
            } else {
                odd += cind;
            }
        }
        return odd == even;
    }
}
