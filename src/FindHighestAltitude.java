public class FindHighestAltitude {
    public int largestAltitude(int[] a) {
        int n = a.length;
        int h = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            h += a[i];
            max = Math.max(max, h);
        }
        return max;
    }
}
