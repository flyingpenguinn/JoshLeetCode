import base.ArrayUtils;

public class TrionicArrayII {

    public long maxSumTrionic(int[] a) {
        int n = a.length;
        int i = 0;
        long res = (long) -1e15;
        int len1 = 0;
        int len2 = 0;
        int len3 = 0;
        long sum = 0;
        while (i < n) {
            if (len1 == 0) {
                int oi1 = i;
                while (i + 1 < n && a[i] < a[i + 1]) {
                    ++i;
                }
                len1 = i - oi1;
                if (len1 == 0) {
                    ++i;
                    sum = 0;
                    continue;
                }
                int j = i - 1;
                long sum12 = 0;
                long maxsum1 = (long) -1e15;
                while (j >= oi1) {
                    sum12 += a[j];
                    maxsum1 = Math.max(maxsum1, sum12);
                    --j;
                }
                sum += maxsum1;
            }
            int oi2 = i;
            while (i + 1 < n && a[i] > a[i + 1]) {
                sum += a[i];
                ++i;
            }
            len2 = i - oi2;
            if (len2 == 0) {
                ++i;
                len1 = 0;
                sum = 0;
                continue;
            }
            int oi3 = i;

            while (i + 1 < n && a[i] < a[i + 1]) {
                sum += a[i];
                if (i - oi3 > 0) {
                    res = Math.max(res, sum);
                }

                ++i;
            }


            len3 = i - oi3;
            if (len3 == 0) {
                ++i;
                len1 = 0;
                sum = 0;
                continue;
            }
            int j = i - 1;
            long sum32 = 0;
            long maxsum3 = (long) -1e15;
            while (j >= oi3) {
                sum32 += a[j];
                maxsum3 = Math.max(maxsum3, sum32);
                --j;
            }
            long rsum = i < n ? sum + a[i] : sum;
            res = Math.max(res, rsum);
            len1 = len3;
            sum = maxsum3;
            len2 = 0;
            len3 = 0;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new TrionicArrayII().maxSumTrionic(ArrayUtils.read1d("[2,993,-791,-635,-569]")));
    }

}
