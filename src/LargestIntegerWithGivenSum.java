public class LargestIntegerWithGivenSum {
    public int largestInteger(int n, int s) {
        char[] r = new char[n];
        for (int i = 0; i < n; ++i) {
            int cd = Math.min(9, s);
            r[i] = (char) ('0' + cd);
            s -= cd;
        }
        if (s > 0) {
            return -1;
        }
        return Integer.valueOf(new String(r));
    }
}
