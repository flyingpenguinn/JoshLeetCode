public class CountNumberOfHomogenousSubstring {
    private int mod = 1000000007;

    public int countHomogenous(String s) {
        char[] sc = s.toCharArray();
        int n = s.length();
        int i = 0;
        long res = 0;
        while (i < n) {
            int j = i;
            while (j < n && sc[j] == sc[i]) {
                int cur = j - i + 1;
                res += cur;
                res %= mod;
                j++;
            }
            i = j;
        }
        return (int) res;
    }
}
