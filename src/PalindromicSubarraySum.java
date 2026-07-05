public class PalindromicSubarraySum {
    // Manacher O(n) palindrome subarray template
    public long getSum(int[] nums) {
        int n = nums.length;

        long[] pref = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            pref[i + 1] = pref[i] + nums[i];
        }

        int[] odd = manacherOdd(nums);
        int[] even = manacherEven(nums);

        long res = 0;

        for (int i = 0; i < n; ++i) {
            int k = odd[i];
            int l = i - k + 1;
            int r = i + k - 1;
            res = Math.max(res, pref[r + 1] - pref[l]);
        }

        for (int i = 0; i < n; ++i) {
            int k = even[i];
            if (k == 0) {
                continue;
            }

            int l = i - k;
            int r = i + k - 1;
            res = Math.max(res, pref[r + 1] - pref[l]);
        }

        return res;
    }

    private int[] manacherOdd(int[] a) {
        int n = a.length;
        int[] d = new int[n];

        int l = 0;
        int r = -1;

        for (int i = 0; i < n; ++i) {
            int k;
            if (i > r) {
                k = 1;
            } else {
                k = Math.min(d[l + r - i], r - i + 1);
            }

            while (i - k >= 0 && i + k < n && a[i - k] == a[i + k]) {
                ++k;
            }

            d[i] = k;

            if (i + k - 1 > r) {
                l = i - k + 1;
                r = i + k - 1;
            }
        }

        return d;
    }

    private int[] manacherEven(int[] a) {
        int n = a.length;
        int[] d = new int[n];

        int l = 0;
        int r = -1;

        for (int i = 0; i < n; ++i) {
            int k;
            if (i > r) {
                k = 0;
            } else {
                k = Math.min(d[l + r - i + 1], r - i + 1);
            }

            while (i - k - 1 >= 0 && i + k < n && a[i - k - 1] == a[i + k]) {
                ++k;
            }

            d[i] = k;

            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
        }

        return d;
    }

}
