public class MaxLengthSubstringWithTwoOccu {
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        int j = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            ++cnt[cind];
            //System.out.println(i+" "+j+" "+Arrays.toString(cnt));
            while (isbad(cnt)) {
                //System.out.println(i+" "+j+" "+Arrays.toString(cnt));
                --cnt[s.charAt(j) - 'a'];
                ++j;
            }
            int cur = i - j + 1;
            res = Math.max(res, cur);
        }
        return res;
    }

    private boolean isbad(int[] cnt) {
        for (int j = 0; j < cnt.length; ++j) {
            if (cnt[j] > 2) {
                return true;
            }
        }
        return false;
    }
}
