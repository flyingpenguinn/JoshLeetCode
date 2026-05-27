package base;

import java.util.HashMap;
import java.util.Map;

public class SqrtDecomp {
    // if seg tree starts to store map consider sqrt decomp
    // O1 merging without Mo algo
    static class SqrtFreq {
        int n;
        int B;
        int m;

        long[] a;
        long[] lazy;
        Map<Long, Integer>[] freq;

        public SqrtFreq(int[] arr) {
            this.n = arr.length;
            this.B = (int) Math.sqrt(n) + 1;
            this.m = (n + B - 1) / B;

            this.a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = arr[i];
            }

            this.lazy = new long[m];
            this.freq = new HashMap[m];

            for (int i = 0; i < m; i++) {
                rebuild(i);
            }
        }

        private void rebuild(int b) {
            freq[b] = new HashMap<>();

            int l = b * B;
            int r = Math.min(n - 1, l + B - 1);

            for (int i = l; i <= r; i++) {
                freq[b].put(a[i], freq[b].getOrDefault(a[i], 0) + 1);
            }
        }

        private void push(int b) {
            if (lazy[b] == 0) {
                return;
            }

            int l = b * B;
            int r = Math.min(n - 1, l + B - 1);

            for (int i = l; i <= r; i++) {
                a[i] += lazy[b];
            }

            lazy[b] = 0;
            rebuild(b);
        }

        public void add(int ql, int qr, long v) {
            int lb = ql / B;
            int rb = qr / B;

            if (lb == rb) {
                push(lb);

                for (int i = ql; i <= qr; i++) {
                    a[i] += v;
                }

                rebuild(lb);
                return;
            }

            push(lb);
            int lend = Math.min(n - 1, (lb + 1) * B - 1);
            for (int i = ql; i <= lend; i++) {
                a[i] += v;
            }
            rebuild(lb);

            push(rb);
            int rstart = rb * B;
            for (int i = rstart; i <= qr; i++) {
                a[i] += v;
            }
            rebuild(rb);

            for (int b = lb + 1; b <= rb - 1; b++) {
                lazy[b] += v;
            }
        }

        public int count(long target) {
            int res = 0;

            for (int b = 0; b < m; b++) {
                long need = target - lazy[b];
                res += freq[b].getOrDefault(need, 0);
            }

            return res;
        }
    }


    static class SqrtDecompSum {
        static class SqrtSum {
            int n;
            int B;
            int m;

            long[] a;      // raw values
            long[] lazy;   // pending add for whole block
            long[] sum;    // current block sum, already includes lazy effect

            public SqrtSum(int[] arr) {
                this.n = arr.length;
                this.B = (int) Math.sqrt(n) + 1;
                this.m = (n + B - 1) / B;

                this.a = new long[n];
                for (int i = 0; i < n; i++) {
                    a[i] = arr[i];
                }

                this.lazy = new long[m];
                this.sum = new long[m];

                for (int b = 0; b < m; b++) {
                    rebuild(b);
                }
            }

            private int blockL(int b) {
                return b * B;
            }

            private int blockR(int b) {
                return Math.min(n - 1, (b + 1) * B - 1);
            }

            private int blockLen(int b) {
                return blockR(b) - blockL(b) + 1;
            }

            private void rebuild(int b) {
                sum[b] = 0;
                for (int i = blockL(b); i <= blockR(b); i++) {
                    sum[b] += a[i];
                }
            }

            private void applyBlock(int b, long delta) {
                lazy[b] += delta;
                sum[b] += delta * blockLen(b);
            }

            private void push(int b) {
                if (lazy[b] == 0) return;

                long delta = lazy[b];
                for (int i = blockL(b); i <= blockR(b); i++) {
                    a[i] += delta;
                }

                lazy[b] = 0;
                rebuild(b);
            }

            public void rangeAdd(int ql, int qr, long delta) {
                int lb = ql / B;
                int rb = qr / B;

                if (lb == rb) {
                    push(lb);
                    for (int i = ql; i <= qr; i++) {
                        a[i] += delta;
                    }
                    rebuild(lb);
                    return;
                }

                push(lb);
                for (int i = ql; i <= blockR(lb); i++) {
                    a[i] += delta;
                }
                rebuild(lb);

                push(rb);
                for (int i = blockL(rb); i <= qr; i++) {
                    a[i] += delta;
                }
                rebuild(rb);

                for (int b = lb + 1; b <= rb - 1; b++) {
                    applyBlock(b, delta);
                }
            }

            public long rangeSum(int ql, int qr) {
                int lb = ql / B;
                int rb = qr / B;

                long res = 0;

                if (lb == rb) {
                    push(lb);
                    for (int i = ql; i <= qr; i++) {
                        res += a[i];
                    }
                    rebuild(lb);
                    return res;
                }

                push(lb);
                for (int i = ql; i <= blockR(lb); i++) {
                    res += a[i];
                }
                rebuild(lb);

                push(rb);
                for (int i = blockL(rb); i <= qr; i++) {
                    res += a[i];
                }
                rebuild(rb);

                for (int b = lb + 1; b <= rb - 1; b++) {
                    res += sum[b];
                }

                return res;
            }

            public long sumAll() {
                long res = 0;
                for (int b = 0; b < m; b++) {
                    res += sum[b];
                }
                return res;
            }
        }
    }
}
