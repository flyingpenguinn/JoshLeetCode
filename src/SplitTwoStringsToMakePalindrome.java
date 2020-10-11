public class SplitTwoStringsToMakePalindrome {
    // key insight: we can match as far as we can between prefix/suffix
    public boolean checkPalindromeFormation(String a, String b) {
        if (ispalin(a, 0, a.length() - 1) || ispalin(b, 0, b.length() - 1)) {
            return true;
        }
        return good(a, b) || good(b, a);
    }


    private boolean good(String a, String b) {
        int n = a.length();
        int i = 0;
        for (; i < n; i++) {
            // make sure remlen >=0
            if (a.charAt(i) != b.charAt(n - 1 - i)) {
                break;
            }
        }
        int remlen = n - 2 * (i);
        if (remlen < 0) {
            return true;
        }
        int bi = n - 1 - i;
        return ispalin(a, i, i + remlen - 1) || ispalin(b, bi - remlen + 1, bi);
    }

    private boolean ispalin(String s, int i, int j) {
        if (i < 0 || j >= s.length()) {
            return false;
        }
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
