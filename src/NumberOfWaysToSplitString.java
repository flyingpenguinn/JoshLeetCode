public class NumberOfWaysToSplitString {
    private long Mod = 1000000007;

    public int numWays(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
        }
        if (count % 3 != 0) {
            return 0;
        }
        if (count == 0) {
            long rt = (n - 1L) * (n - 2L) / 2; // compositions not allowing empty: c(n-1, k-1). k = 3 here
            return (int) (rt % Mod);
        }
        int i1 = 0;
        int c = 0;
        while (i1 < n) {
            if (s.charAt(i1) == '1') {
                c++;
            }
            if (c == count / 3) {
                break;
            } else {
                i1++;
            }
        }
        int i2 = i1 + 1;
        while (i2 < n && s.charAt(i2) == '0') {
            i2++;
        }
        // i2 now at possible start of s2. i1... i2-1 are possible first ends
        int j1 = i2;
        while (j1 < n) {
            if (s.charAt(j1) == '1') {
                c++;
            }
            if (c == 2 * count / 3) {
                break;
            } else {
                j1++;
            }
        }
        int j2 = j1 + 1;
        while (j2 < n && s.charAt(j2) == '0') {
            j2++;
        }
        // j2 now at start of s3. j1...j2-1 are possible of second ends

        long rt = (0L + i2 - i1) * (0L + j2 - j1);
        return (int) (rt % Mod);
    }
}
