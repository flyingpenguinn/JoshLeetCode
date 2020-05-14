public class PartitionArrayIntoDisjointIntervals {
    public int partitionDisjoint(int[] a) {
        int n = a.length;
        // just need a min array to get rolling mins, dont need a treeset
        int[] min = new int[n];
        min[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            min[i] = Math.min(min[i + 1], a[i]);
        }
        int fb = a[0];
        for (int i = 1; i < n; i++) {
            int ls = min[i];
            if (fb <= ls) {
                return i;
            }
            fb = Math.max(fb, a[i]);
        }
        return -1;
    }
}
