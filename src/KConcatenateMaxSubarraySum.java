import base.ArrayUtils;


public class KConcatenateMaxSubarraySum {
    // Note this is not similar to max circular subarray- in that problem we can deal with length ==n at most
    int Min = Integer.MIN_VALUE;
    int Mod = 1000000007;

    public int kConcatenationMaxSum(int[] a, int k) {
        int kad = Min;
        int n = a.length;
        int pref = 0;
        int maxpref = Min;
        int pkad = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            pref += a[i];
            maxpref = Math.max(maxpref, pref);
            if (pkad < 0) {
                pkad = a[i];
            } else {
                pkad += a[i];
            }
            kad = Math.max(kad, pkad);
            sum += a[i];
        }
        if (k == 1) {
            // ==1,just kadane
            return kad;
        }
        if (kad < 0) {
            // kadane <0, all <0
            return 0;
        }
        int suf = 0;
        int maxsuf = Min;
        for (int i = n - 1; i >= 0; i--) {
            suf += a[i];
            maxsuf = Math.max(maxsuf, suf);
        }
        if (sum > 0) {
            // k-2 + biggest of two ends, or kadane, when sum>0
            long kt = (k - 2L) * sum + maxpref + maxsuf;
            long bg = Math.max(kad, kt);
            return (int) (bg % Mod);
        } else {
            // either head+tail or kadane, when sum <0
            int rt = Math.max(maxpref + maxsuf, kad);
            return rt % Mod;
        }
    }

    public static void main(String[] args) {
        //     System.out.println(sol.kConcatenationMaxSum(ArrayUtils.read1d("2,-5,1,0,-2,-2,2"), 2));
        System.out.println(new KConcatenateMaxSubarraySum().kConcatenationMaxSum(ArrayUtils.read1d("[5,-3,5]"), 2));
    }
}
