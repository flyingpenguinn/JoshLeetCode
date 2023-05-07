public class NumAdjEelementSameColor {
    public int[] colorTheArray(int n, int[][] qs) {
        int[] a = new int[n];
        int[] res = new int[qs.length];

        for (int i = 0; i < qs.length; i++) {
            int index = qs[i][0];
            int color = qs[i][1];
            int oldcolor = a[index];
            int prev = (index - 1 >= 0) ? a[index - 1] : 0;
            int next = (index + 1 < n) ? a[index + 1] : 0;
            int cur = (i == 0 ? 0 : res[i - 1]);
            if (oldcolor != 0 && prev == oldcolor) {
                --cur;
            }
            if (oldcolor != 0 && next == oldcolor) {
                --cur;
            }
            if (prev == color) {
                ++cur;
            }
            if (next == color) {
                ++cur;
            }
            a[index] = color;
            res[i] = cur;
        }

        return res;
    }
}
