public class DecodeXoredPerm {
    // it's all about accumulative xors
    // xor all = 1^2^3... so we already  have one accumulative!
    public int[] decode(int[] a) {
        int n = a.length;
        int[] res = new int[n + 1];
        int xor = 0;
        for (int i = 0; i < n; i += 2) {
            xor ^= a[i];
        }
        // xor = a1^a2^...an-1
        // all = a1^a2^...an-1^an, so we have an now
        int all = 0;
        for (int i = 1; i <= n + 1; i++) {
            all ^= i;
        }
        res[n] = all ^ xor;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i + 1] ^ a[i];
        }
        return res;
    }
}
