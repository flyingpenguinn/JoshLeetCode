package base;

public class FenWicks {
    static class FenWick {
        private int[] bit;

        public FenWick(int n) {
            int[] bit = new int[n];
        }

        private int q(int i) {
            int res = 0;
            while (i > 0) {
                res += bit[i];
                i -= i & (-i);
            }
            return res;
        }

        private void u(int i, int d) {

            while (i < bit.length) {
                bit[i] += d;
                i += i & (-i);
            }
        }
    }
}
