public class FindOriginalArrayOfPrefix {
    public int[] findArray(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        for (int i = n - 1; i >= 1; --i) {
            res[i] = a[i] ^ a[i - 1];
        }
        res[0] = a[0];
        return res;
    }
}
