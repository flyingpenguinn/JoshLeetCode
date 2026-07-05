public class UniqueMiddleElement {
    public boolean isMiddleElementUnique(int[] a) {
        int n = a.length;
        int mid = n / 2;
        int mv = a[mid];
        int cnt = 0;
        for (int ai : a) {
            if (ai == mv) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
