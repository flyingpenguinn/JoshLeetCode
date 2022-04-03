public class FindTriangularSumOfArray {
    public int triangularSum(int[] a) {
        int n = a.length;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < i; ++j) {
                a[j] = (a[j] + a[j + 1]) % 10;
            }
        }
        return a[0];
    }
}
