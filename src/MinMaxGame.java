public class MinMaxGame {
    // O1 space
    public int minMaxGame(int[] a) {
        int n = a.length;
        while (n > 0) {
            for (int i = 0; i < n / 2; ++i) {
                if (i % 2 == 0) {
                    a[i] = Math.min(a[2 * i], a[2 * i + 1]);
                } else {
                    a[i] = Math.max(a[2 * i], a[2 * i + 1]);
                }

            }
            n /= 2;
        }
        return a[0];
    }
}
