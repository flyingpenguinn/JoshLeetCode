public class DesignMostRecentlyUsedQueue {
    class MRUQueue {
        private int[] a;
        private int[] bit;
        private int size;
        private int n;

        public MRUQueue(int n) {
            a = new int[n + 2001];
            bit = new int[n + 2001];
            for (int i = 1; i <= n; i++) {
                a[i] = i;
            }
            for (int i = 1; i <= n; i++) {
                u(bit, i, 1);
            }
            size = n;
        }

        // binary search the position that has k set bits before it
        public int fetch(int k) {
            int l = 1;
            int u = size;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                if (q(bit, mid) >= k) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            a[size + 1] = a[l];
            u(bit, l, -1);
            u(bit, size + 1, 1);
            size++;
            return a[l];
        }

        private int q(int[] bit, int i) {
            int sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= i & (-i);
            }
            return sum;
        }

        private void u(int[] bit, int i, int d) {
            while (i < bit.length) {
                bit[i] += d;
                i += i & (-i);
            }
        }
    }
}
