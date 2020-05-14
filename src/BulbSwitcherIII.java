public class BulbSwitcherIII {
    // whenever lighted == current max we have a moment
    public int numTimesAllBlue(int[] a) {
        int n = a.length;
        int max = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, a[i]);
            int lights = i + 1;
            if (lights == max) {
                r++;
            }
        }
        return r;
    }
}
