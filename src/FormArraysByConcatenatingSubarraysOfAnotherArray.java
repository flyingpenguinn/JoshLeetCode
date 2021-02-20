public class FormArraysByConcatenatingSubarraysOfAnotherArray {
    public boolean canChoose(int[][] gs, int[] a) {

        return canChoose(gs, a, 0, 0);
    }

    private boolean canChoose(int[][] gs, int[] a, int i, int j) {
        if (i == gs.length) {
            return true;
        }
        if (j == a.length) {
            return false;
        }

        if (eq(gs[i], a, j)) {
            // greedy: if we can match early, go with it, as it will leave more room for the next one
            return canChoose(gs, a, i + 1, j + gs[i].length);
        } else {
            return canChoose(gs, a, i, j + 1);
        }
    }

    private boolean eq(int[] b, int[] a, int j) {

        if (a.length - j < b.length) {

            return false;
        }
        for (int i = 0; i < b.length; i++) {
            if (b[i] != a[j]) {
                return false;
            }
            j++;
        }

        return true;
    }
}
