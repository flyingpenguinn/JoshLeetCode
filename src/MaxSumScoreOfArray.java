public class MaxSumScoreOfArray {
    public long maximumSumScore(int[] a) {
        long sum = 0;
        for (int ai : a) {
            sum += ai;
        }
        long left = 0;
        long res = (long) -1e9;
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            long ileft = left + a[i];
            long iright = sum - left;
            long cur = Math.max(ileft, iright);
            //  System.out.println(ileft+" "+iright+" "+cur);
            res = Math.max(res, cur);
            left += a[i];
        }
        return res;
    }
}
