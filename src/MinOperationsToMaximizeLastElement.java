public class MinOperationsToMaximizeLastElement {
    private int Max = (int) 1e9;

    public int minOperations(int[] a, int[] b) {
        int n = a.length;
        int way1 = find(a, b, a[n - 1], b[n - 1]);
        int way2 = 1 + find(a, b, b[n - 1], a[n - 1]);
        int rt = Math.min(way1, way2);
        return rt >= Max ? -1 : rt;
    }

    private int find(int[] a, int[] b, int ma, int mb) {
        int n = a.length;
        int res = 0;
        for (int i = n - 2; i >= 0; --i) {
            if (a[i] > ma) {
                if (b[i] > ma) {
                    return Max;
                }
                if (a[i] > mb) {
                    return Max;
                }
                ++res;
            } else if (b[i] > mb) {
                if (a[i] > mb) {
                    return Max;
                }
                if (b[i] > ma) {
                    return Max;
                }
                ++res;
            }
        }
        return res;
    }
}
