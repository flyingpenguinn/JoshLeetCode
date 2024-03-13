public class MinTimeToRevertWordToInitialStateIandII {
    // Use KMP to get all prefixes that are also suffix
    // Function to generate the KMP table
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        int res = getAllPrefixesSuffixes(word, k);
        if (res == -1) {
            return (int) Math.ceil(n * 1.0 / k);
        } else {
            return res;
        }
    }

    private static int[] computeKMPTable(String pattern) {
        int n = pattern.length();
        char[] p = pattern.toCharArray();
        int[] lps = new int[n];
        lps[0] = 0; // lps[0] is always 0
        int len = 0;

        for (int i = 1; i < n; ++i) {
            while (len > 0 && p[len] != p[i]) {
                len = lps[len - 1];
            }
            if (p[len] == p[i]) {
                ++len;
            }
            lps[i] = len;
        }
        return lps;
    }

    // Function to get all prefixes that are also suffixes
    public static int getAllPrefixesSuffixes(String pattern, int k) {
        int[] lps = computeKMPTable(pattern);
        int n = pattern.length();
        int res = -1;
        // Use the KMP table to find all prefixes that are also suffixes
        int length = lps[pattern.length() - 1]; // Start with the longest
        while (length > 0) {
            int prelen = n - length;
            if (prelen % k == 0) {
                return prelen / k;
            }
            length = lps[length - 1]; // Get the next length from the table
        }

        return res;
    }
}


class MinTimeToRevertStringRollingHash {
    // rolling hash, rabin karp
    private long base = 31;
    private long Mod = (long) (1e9 + 7);

    public int minimumTimeToInitialState(String s, int k) {
        int n = s.length();
        long cur = 0;
        long[] f = new long[n];
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) + 1;
            cur = cur * base + cind;
            cur %= Mod;
            f[i] = cur;
        }
        cur = 0;
        long cbase = 1;
        long[] b = new long[n];
        for (int i = n - 1; i >= 1; --i) {
            int cind = s.charAt(i) + 1;
            cur += cbase * cind;
            cur %= Mod;
            b[i] = cur;
            cbase *= base;
            cbase %= Mod;
        }
        for (int times = 1; times * k < n; ++times) {
            int end = times * k;
            int rlen = n - end;
            long fv = f[rlen - 1];
            long rv = b[end];
            //  System.out.println("end="+end+" fv="+fv+" rv="+rv+" rlen="+rlen);
            if (fv == rv && s.substring(end).equals(s.substring(0, rlen))) {
                return times;
            }
        }
        return (int) (Math.ceil(n * 1.0 / k));
    }
}
