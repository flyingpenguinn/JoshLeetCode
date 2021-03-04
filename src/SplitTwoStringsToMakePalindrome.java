public class SplitTwoStringsToMakePalindrome {
    // key insight: we can match as far as we can between prefix/suffix
    public boolean checkPalindromeFormation(String a, String b) {
        int n = a.length();
        if (palin(a, 0, n - 1) || palin(b, 0, n - 1)) {
            return true;
        }
        return check(a, b) || check(b, a);
    }

    private boolean palin(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(String a, String b) {
        int n = a.length();
        int i = 0;
        int j = n - 1;
        int len = 0;
        // dont need to check for every pair! just stop when length is too long or unmatch
        while (i < n && j >= 0 && n - 2 * (len + 1) >= 0) {
            if (a.charAt(i) != b.charAt(j)) {
                break;
            }
            len++;
            i++;
            j--;
        }
        int gap = n - 2 * len;
        if (palin(a, i, i + gap - 1) || palin(b, j - gap + 1, j)) {
            return true;
        }
        return false;
    }
}
