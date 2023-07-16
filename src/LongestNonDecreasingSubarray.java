public class LongestNonDecreasingSubarray {
    public int maxNonDecreasingLength(int[] a, int[] b) {
        int n = a.length;
        int lena = 1;
        int lenb = 1;
        int va = a[0];
        int vb = b[0];
        int res = 1;
        for (int i = 1; i < n; ++i) {
            int ca = 1;
            if (a[i] >= va) {
                ca = Math.max(ca, lena + 1);
            }
            if (a[i] >= vb) {
                ca = Math.max(ca, lenb + 1);
            }
            //   System.out.println(ca);
            res = Math.max(res, lena);
            int cb = 1;
            if (b[i] >= va) {
                cb = Math.max(cb, lena + 1);
            }
            if (b[i] >= vb) {
                cb = Math.max(cb, lenb + 1);
            }

            //   System.out.println(cb);

            lena = ca;

            res = Math.max(res, lena);
            lenb = cb;
            res = Math.max(res, lenb);
            va = a[i];
            vb = b[i];
        }
        return res;
    }
}
