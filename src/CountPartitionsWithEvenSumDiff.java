public class CountPartitionsWithEvenSumDiff {
    public int countPartitions(int[] a) {
        int n = a.length;
        int all = 0;
        for (int ai : a) {
            all += ai;
        }
        int left = 0;
        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            left += a[i];
            int right = all - left;
            int diff = left - right;
            //   System.out.println(left+" "+right);
            if (diff % 2 == 0) {
                ++res;
            }
        }
        return res;
    }
}
