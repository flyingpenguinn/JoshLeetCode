public class MinDominoRotation {
    // try out a[0], b[0] for fixing a or b...
    private int Max = 10000000;

    public int minDominoRotations(int[] a, int[] b) {
        int doa = Math.min(dorotate(a, b, a[0]), dorotate(a, b, b[0]) + 1);
        int dob = Math.min(dorotate(b, a, b[0]), dorotate(b, a, a[0]) + 1);
        int rt = Math.min(doa, dob);
        return rt >= Max ? -1 : rt;
    }

    // always match a to the target number t
    private int dorotate(int[] a, int[] b, int t) {
        int res = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == t) {
                continue;
            } else if (b[i] == t) {
                res++;
            } else {
                return Max;
            }
        }
        return res;
    }
}
