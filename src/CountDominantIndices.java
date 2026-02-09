public class CountDominantIndices {
    public int dominantIndices(int[] a) {
        int n = a.length;
        double sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += a[i];
        }
        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            sum -= a[i];
            double avg = sum / (n - 1 - i);
            if (a[i] > avg) {
                ++res;
            }
        }
        return res;
    }
}
