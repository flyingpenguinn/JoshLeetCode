public class FindMinLogTransportation {
    // always best to cut off k
    public long minCuttingCost(int n, int m, int k) {
        long cn = 0;
        if (n > k) {
            cn = 1L * k * (n - k);
        }
        long cm = 0;
        if (m > k) {
            cm = 1L * k * (m - k);
        }
        return cn + cm;
    }
}
