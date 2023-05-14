public class NeiboringBitwiseXor {
    // each number showed up twice
    public boolean doesValidArrayExist(int[] a) {
        int n = a.length;
        if (n == 1) {
            return a[0] == 0;
        }
        int xor = a[0];
        for (int i = 1; i < n; ++i) {
            xor ^= a[i];
        }
        return xor == 0;
    }
}
