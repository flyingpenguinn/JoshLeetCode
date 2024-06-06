public class BitwiseOrOfAdjacentElements {
    public int[] orArray(int[] a) {
        int n = a.length;
        int[] res = new int[n - 1];
        for (int i = 0; i + 1 < n; ++i) {
            res[i] = a[i] | a[i + 1];
        }
        return res;
    }
}
