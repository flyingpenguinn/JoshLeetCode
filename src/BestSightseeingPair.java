public class BestSightseeingPair {

    // asking for a[i]+a[j]+i-j. for a certain i, we know a[i]+i. a[j]-j can be cahed from n-1 to i
    public int maxScoreSightseeingPair(int[] a) {
        int n = a.length;
        int maxajmj = a[n - 1] - (n - 1);
        int max = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            int cur = a[i] + i + maxajmj;
            max = Math.max(max, cur);
            maxajmj = Math.max(maxajmj, a[i] - i);
        }
        return max;
    }
}
