public class MinDominoRotation {
    // try out a[0], b[0] for fixing a or b...
    public int minDominoRotations(int[] a, int[] b) {
        int[] lasts = {a[0], b[0]};
        int vb = a.length + 1;
        int min = vb;
        int[] js = {0, 1};
        for (int last : lasts) {
            for (int j : js) {
                int cur = 0;
                for (int i = 0; i < a.length; i++) {
                    if (j == 0) {
                        if (a[i] == last) {
                            // nothing
                        } else if (b[i] == last) {

                            cur++;
                        } else {
                            cur = vb;
                            break;
                        }
                    } else {
                        if (b[i] == last) {
                            // nothing

                        } else if (a[i] == last) {
                            cur++;
                        } else {
                            cur = vb;
                            break;
                        }
                    }
                }
                min = Math.min(min, cur);
            }
        }

        return min >= vb ? -1 : min;
    }
}
