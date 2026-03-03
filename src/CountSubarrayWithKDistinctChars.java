import base.ArrayUtils;

public class CountSubarrayWithKDistinctChars {

    public long countSubarrays(int[] a, int k, int m) {
        int n = a.length;
        int[] cnt1 = new int[100001];
        int[] cnt2 = new int[100001];
        int l1 = 0;
        int l2 = 0;
        int d1 = 0;
        int d2 = 0;
        int g2 = 0;
        long res = 0;
        for (int r = 0; r < n; ++r) {
            int rv = a[r];
            ++cnt1[rv];
            if (cnt1[rv] == 1) {
                ++d1;
            }
            ++cnt2[rv];
            if (cnt2[rv] == 1) {
                ++d2;
            }
            if (cnt2[rv] == m) {
                ++g2;
            }
            // l1 must be first making d <=k
            while (d1 > k) {
                int lv = a[l1];
                --cnt1[lv];
                if (cnt1[lv] == 0) {
                    --d1;
                }
                ++l1;
            }
            while (l2 < l1) {
                int lv = a[l2];
                --cnt2[lv];
                if (cnt2[lv] == 0) {
                    --d2;
                }
                if (cnt2[lv] == m - 1) {
                    --g2;
                }
                ++l2;
            }
            if (d1 == k) {
                while (d2 == k && g2 == k) {
                    int lv = a[l2];
                    if (cnt2[lv] - 1 >= m) {
                        --cnt2[lv];
                        if (cnt2[lv] == 0) {
                            --d2;
                        }
                        ++l2;
                    } else {
                        break;
                    }
                }
                if(d2==k && g2==k){
                    int diff = l2-l1+1;
                    res += diff;
                }
            }
        }
        return res;
    }

    static void main() {
        System.out.println(new CountSubarrayWithKDistinctChars().countSubarrays(ArrayUtils.read1d("[1,2,1,2,2]"), 2, 2));
    }

}
