public class DecodeXorArray {
    public int[] decode(int[] a, int first) {
        int n = a.length;
        int[] res = new int[n + 1];
        res[0] = first;
        for (int i = 1; i <= n; i++) {
            res[i] = a[i - 1] ^ res[i - 1];
        }
        return res;
    }
}
