public class WaysToSplitArrayIntoThreeSubarrays {
    // given i, using binary search to find the sweet spot of j! reason is j can't be too small or too big, but needs to be in a range
    private int Mod = 1000000007;

    public int waysToSplit(int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        int all = sum[n - 1];
        int i = 0;
        int j = 1;
        long res = 0;
        while (j < n - 1 || i + 1 < j) {
            while (j < n - 1 && sum[j] - sum[i] < sum[i]) {
                j++;
            }
            //     System.out.println("judging..."+j+" "+(all-sum[j])+" vs "+(sum[j]-sum[i]));
            while (j < n - 1 && sum[j] - sum[i] <= all - sum[j]) {
                res++;
                j++;
            }
            // j-1 is the last good pos
            if (i + 1 < j) {
                i++;
                int goodj = binary(sum, i + 1, j - 1, 2 * sum[i]);
                //    System.out.println(i+" "+j+" vs "+goodj);
                res += (j - goodj);
                res %= Mod;
            } else {
                break;
            }
        }
        return (int) res;
    }

    // from i to j find the first place where sum[i]<=sum[j]-sum[i], i.e sum[j]>=2*sum[i]
    private int binary(int[] sum, int i, int j, int t) {
        int l = i;
        int u = j;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (sum[mid] < t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }
}
