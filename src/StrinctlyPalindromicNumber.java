public class StrinctlyPalindromicNumber {
    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i <= n - 2; ++i) {
            String str = Integer.toString(n, i);
            // System.out.println(str);
            if (!ispalin(str)) {
                return false;
            }
        }
        return true;
    }

    private boolean ispalin(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
