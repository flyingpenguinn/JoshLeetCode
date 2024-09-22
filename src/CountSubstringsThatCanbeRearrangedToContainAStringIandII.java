public class CountSubstringsThatCanbeRearrangedToContainAStringIandII {
    public long validSubstringCount(String word1, String word2) {
        int[] m2 = new int[26];
        for (char c : word2.toCharArray()) {
            ++m2[c - 'a'];
        }
        int j = 0;
        int n = word1.length();
        int[] m1 = new int[26];
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int cind = word1.charAt(i) - 'a';
            ++m1[cind];
            if (good(m1, m2)) {
                while (j <= i && good(m1, m2)) {
                    long ccount = n - i;
                    res += ccount;
                    int jind = word1.charAt(j) - 'a';
                    --m1[jind];
                    ++j;
                }
            }
        }
        return res;
    }

    private boolean good(int[] m1, int[] m2) {
        for (int i = 0; i < 26; ++i) {
            if (m2[i] == 0) {
                continue;
            }
            if (m1[i] < m2[i]) {
                return false;
            }
        }
        return true;
    }
}
