public class MaxFreqAfterSubarrayOperations {
    public int maxFrequency(int[] a, int k) {
        int n = a.length;
        int ks = 0;
        for (int ai : a) {
            if (ai == k) {
                ++ks;
            }
        }
        int res = ks;
        for (int target = 1; target <= 50; ++target) {
            if (target == k) {
                continue;
            }
            int[] na = new int[n];
            for (int i = 0; i < n; i++) {
                if (a[i] == target) {
                    na[i] = 1;
                } else if (a[i] == k) {
                    na[i] = -1;
                }
            }
            int minsum = 0;
            int csum = 0;
            int bestsum = 0;
            for (int i = 0; i < n; i++) {
                csum += na[i];
                int seg = csum - minsum;
                bestsum = Math.max(seg, bestsum);
                minsum = Math.min(minsum, csum);
            }
            int cres = bestsum + ks;
            res = Math.max(res, cres);
        }
        return res;
    }
}
