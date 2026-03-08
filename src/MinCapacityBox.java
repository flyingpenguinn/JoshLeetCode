public class MinCapacityBox {
    public int minimumIndex(int[] a, int t) {
        int n = a.length;
        int mincap = (int) (1e9);
        int mini = -1;
        for (int i = 0; i < n; ++i) {
            if (a[i] < t) {
                continue;
            }
            if (a[i] < mincap) {
                mincap = a[i];
                mini = i;
            }
        }
        return mini;
    }
}
