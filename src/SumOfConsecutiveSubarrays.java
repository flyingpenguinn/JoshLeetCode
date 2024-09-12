public class SumOfConsecutiveSubarrays {

    private long Mod = (long) (1e9 + 7);

    public int getSum(int[] a) {
        // contribution of each number = current sum + count*a[i]
        int n = a.length;
        long count1 = 0;
        long count2 = 0;
        long sum1 = 0;
        long sum2 = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || a[i] != a[i - 1] + 1) {
                count1 = 0;
                sum1 = 0;
            }
            if (i == 0 || a[i] != a[i - 1] - 1) {
                count2 = 0;
                sum2 = 0;
            }
            sum1 += (++count1) * a[i];
            sum2 += (++count2) * a[i];
            res += sum1 + sum2 - a[i]; // subtract the intersection
            res %= Mod;
        }
        return (int) res;
    }
}
