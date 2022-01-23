public class CounTheHiddenSequences {
    public int numberOfArrays(int[] diffs, int lower, int upper) {
        int n = diffs.length;
        long min = (int) 1e9;
        long max = (int) (-1e9);
        long delta = 0;
        for (int i = 0; i < n; ++i) {
            delta += diffs[i];
            min = Math.min(min, delta);
            max = Math.max(max, delta);
        }
        long end = upper - Math.max(max, 0);
        long start = lower - Math.min(min, 0);
        return (int) Math.max(end - start + 1, 0);
    }
}
