public class RepeatedSubstringPattern {
    // O(nsqrt(n) only check factor of n
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        if (n == 0) {
            return false;
        }
        for (int len = n / 2; len >= 1; len--) {
            if (n % len != 0) {
                continue;
            }
            int j = len;
            boolean bad = false;
            while (j + len - 1 < n) {
                for (int k = 0; k < len; k++) {
                    if (s.charAt(k) != s.charAt(j + k)) {
                        bad = true;
                        break;
                    }
                }
                j += len;
            }
            if (!bad) {
                return true;
            }

        }
        return false;
    }
}
