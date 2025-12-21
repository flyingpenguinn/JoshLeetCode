public class MinCostAllCharsEqual {
    public long minCost(String s, int[] cost) {
        int n = s.length();
        long[] sumcost = new long[26];
        long total = 0;
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            sumcost[cind] += cost[i];
            total += cost[i];
        }
        long maxsum = 0;
        for (int i = 0; i < 26; ++i) {
            maxsum = Math.max(maxsum, sumcost[i]);
        }
        return total - maxsum;
    }
}
