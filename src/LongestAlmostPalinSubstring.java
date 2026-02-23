public class LongestAlmostPalinSubstring {
    public int almostPalindromic(String s) {
        int n = s.length();
        int res = 0;
        char[] c = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            int cur = getgood(c, i, i);
            res = Math.max(res, cur);
        }
        for (int i = 0; i + 1 < n; ++i) {
            int cur = getgood(c, i, i + 1);
            res = Math.max(res, cur);
        }
        return res;
    }

    private int getgood(char[] c, int s1, int s2) {
        int n = c.length;
        int i = s1;
        int j = s2;
        int res = 0;
        boolean extra = false;
        while (i >= 0 && j < n) {
            if (c[i] == c[j]) {
                if (i == j) {
                    ++res;
                } else {
                    res += 2;
                }

                --i;
                ++j;
            } else {
                extra = true;
                int extra1 = getgood2(c, i - 1, j);
                int extra2 = getgood2(c, i, j + 1);
                res += Math.max(extra1, extra2);
                break;
            }
        }
        return Math.min(res+1, n);
    }

    private int getgood2(char[] c, int s1, int s2) {

        int n = c.length;

        int i = s1;
        int j = s2;
        int res = 0;
        while (i >= 0 && j < n) {
            if (c[i] == c[j]) {
                res += 2;
                --i;
                ++j;
            } else {
                break;
            }
        }
        return res;
    }

    static void main() {
        System.out.println(new LongestAlmostPalinSubstring().almostPalindromic("aabab"));
        System.out.println(new LongestAlmostPalinSubstring().almostPalindromic("abca"));
        System.out.println(new LongestAlmostPalinSubstring().almostPalindromic("abba"));
        System.out.println(new LongestAlmostPalinSubstring().almostPalindromic("zzabba"));
    }

}
