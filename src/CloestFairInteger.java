public class CloestFairInteger {
    private int[] table = {-1, -1, 10, -1, 1001, -1, 100011, -1, 10000111, -1, 1000001111};

    public int closestFair(int n) {
        int k = n;
        while (true) {
            int dk = digs(k);
            if (dk % 2 == 1) {
                return table[dk + 1];
            }
            if (fair(k, dk)) {
                return k;
            } else {
                ++k;
            }
        }
    }

    private int digs(int k) {
        int res = 0;
        while (k > 0) {
            ++res;
            k /= 10;
        }
        return res;
    }

    private boolean fair(int k, int digits) {
        int odds = 0;
        while (k > 0) {
            odds += (k % 10 % 2);
            k /= 10;
        }
        return odds * 2 == digits;
    }
}
