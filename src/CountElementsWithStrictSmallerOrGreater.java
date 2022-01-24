public class CountElementsWithStrictSmallerOrGreater {
    public int countElements(int[] a) {
        int n = a.length;
        int min = (int) 1e9;
        int max = (int) -1e9;
        for (int i = 0; i < n; ++i) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] > min && a[i] < max) {
                ++res;
            }
        }
        return res;
    }
}
