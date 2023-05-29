public class MinCostToMakeAllCharsEqual {
    // couldnt figure out in the contest...
    public long minimumCost(String s) {
        char[] sc = s.toCharArray();
        int n = s.length();
        long res = 0;
        for (int i = 0; i + 1 < n; ++i) {
            if (sc[i] != sc[i + 1]) {
                res += Math.min(i + 1, n - i - 1);
            }
        }
        return res;
    }
}
