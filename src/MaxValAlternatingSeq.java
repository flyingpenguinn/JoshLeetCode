public class MaxValAlternatingSeq {
    public long maximumValue(int n, int s, int m) {
        if (n == 1) {
            return s;
        }
        if (n % 2 == 0) {
            long v1 = s + m;
            long items = n / 2;
            long end = v1 + (items - 1) * (m - 1);
            return end;
        } else {
            long v1 = s + m;
            long items = n / 2;
            long end = v1 + (items - 1) * (m - 1);
            return end;
        }
    }
}
