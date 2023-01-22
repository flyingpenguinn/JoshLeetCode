public class MinCommonValue {
    public int getCommon(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        int i = 0;
        int j = 0;
        while (i < an && j < bn) {
            if (a[i] == b[j]) {
                return a[i];
            } else if (a[i] < b[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return -1;
    }
}
