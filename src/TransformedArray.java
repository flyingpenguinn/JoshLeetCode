public class TransformedArray {
    public int[] constructTransformedArray(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (v > 0) {
                int j = i;
                while (v > 0) {
                    ++j;
                    j %= n;
                    --v;
                }
                res[i] = a[j];
            } else if (v < 0) {
                int j = i;
                int av = Math.abs(v);
                while (av > 0) {
                    --j;
                    if (j < 0) {
                        j += n;
                    }
                    --av;
                }
                res[i] = a[j];
            } else {
                res[i] = a[i];
            }
        }
        return res;
    }
}
