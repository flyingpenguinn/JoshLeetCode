public class TakeKOfEachCharFromLeftOrRight {
    // >=k on the two sides: <=k at the some internal segments
    public int takeCharacters(String s, int k) {
        int n = s.length();
        int[] count = new int[3];
        for (char c : s.toCharArray()) {
            ++count[c - 'a'];
        }
        for (int i = 0; i < 3; ++i) {
            count[i] -= k;
            if (count[i] < 0) {
                return -1;
            }
        }
        int j = 0;
        int res = 0;
        int[] nc = new int[3];
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            ++nc[cind];
            while (nc[cind] > count[cind]) {
                int jcind = s.charAt(j) - 'a';
                --nc[jcind];
                ++j;
            }
            int cur = i - j + 1;
            res = Math.max(res, cur);
        }
        return n - res;
    }
}
