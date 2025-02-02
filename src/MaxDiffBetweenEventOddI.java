public class MaxDiffBetweenEventOddI {
    public int maxDifference(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            ++cnt[cind];
        }
        int maxodd = 0;
        int mineven = 100000;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] == 0) {
                continue;
            }
            if (cnt[i] % 2 == 1) {
                maxodd = Math.max(maxodd, cnt[i]);
            } else {
                mineven = Math.min(mineven, cnt[i]);
            }
        }
        return maxodd - mineven;
    }
}
