public class BitwiseOrOfAllsubsequenceSums {
    public long subsequenceSumOr(int[] a) {
        long res = 0;
        int n = a.length;
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            res |= a[i];
            sum += a[i];
            res |= sum;
        }
        return res;
    }
}
