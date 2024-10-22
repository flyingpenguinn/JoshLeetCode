public class CountSubstringsWithKfrequencyCharsII {
    // do the counter first then subtract.
    public long numberOfSubstrings(String s, int k) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = s.charAt(i) - 'a';
        }
        long other = atmost(a, k - 1);
        long all = 1L * n * (n + 1) / 2;
        return all - other;
    }


    private long atmost(int[] a, int k) {
        if (k == 0) {
            return 0;
        }
        int n = a.length;
        int start = 0;
        int[] m = new int[26];
        long res = 0;
        for (int i = 0; i < n; i++) {
            ++m[a[i]];
            while (bad(m, k)) {
                --m[a[start]];
                start++;
            }
            // start...i good, have at most k chars
            res += i - start + 1L;
        }
        return res;
    }

    private boolean bad(int[] m, int k) {
        for (int mi : m) {
            if (mi > k) {
                return true;
            }
        }
        return false;
    }
}
