public class CountSubarrayWithKDistinctChars {
    class Solution {
        public long countSubarrays(int[] nums, int k, int m) {
            int n = nums.length;
            int[] cnt1 = new int[100001];
            int[] cnt2 = new int[100001];

            int l1 = 0;
            int l2 = 0;

            int d1 = 0;

            int d2 = 0;
            int g2 = 0;

            long res = 0;

            for (int r = 0; r < n; r++) {
                int x = nums[r];

                if (cnt1[x] == 0) {
                    d1++;
                }
                cnt1[x]++;

                if (cnt2[x] == 0) {
                    d2++;
                }
                cnt2[x]++;
                if (cnt2[x] == m) {
                    g2++;
                }

                while (d1 > k) {
                    int y = nums[l1];
                    cnt1[y]--;
                    if (cnt1[y] == 0) {
                        d1--;
                    }
                    l1++;
                }

                while (l2 < l1) {
                    int y = nums[l2];
                    if (cnt2[y] == m) {
                        g2--;
                    }
                    cnt2[y]--;
                    if (cnt2[y] == 0) {
                        d2--;
                    }
                    l2++;
                }

                if (d1 == k) {
                    while (d2 == k && g2 == k) {
                        int y = nums[l2];
                        if (cnt2[y] - 1 >= m) {
                            if (cnt2[y] == m) {
                                g2--;
                            }
                            cnt2[y]--;
                            if (cnt2[y] == 0) {
                                d2--;
                            }
                            l2++;
                        } else {
                            break;
                        }
                    }

                    if (d2 == k && g2 == k) {
                        res += (long) (l2 - l1 + 1);
                    }
                }
            }

            return res;
        }
    }
}
