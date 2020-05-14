public class PourWater {
    public int[] pourWater(int[] h, int v, int k) {
        for (int i = 1; i <= v; i++) {
            int fall = check(h, k, -1);
            if (fall != k) {
                h[fall]++;
            } else {
                fall = check(h, k, 1);
                h[fall]++;
            }
        }
        return h;
    }

    // the last "change" on each side. change means < canidate
    private int check(int[] h, int k, int delta) {
        int candidate = k;
        int i = candidate;
        while (i >= 0 && i < h.length) {
            if (h[i] > h[candidate]) {
                return candidate;
            }
            if (h[i] < h[candidate]) {
                candidate = i;
            }
            i += delta;
        }
        return candidate;
    }
}
