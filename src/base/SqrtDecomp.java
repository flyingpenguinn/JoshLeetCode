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

        // Raw values. If lazy[b] != 0, values in block b do not yet include it.
        long[] a;

        // Pending add applied logically to every value in the block.
        long[] lazy;

        // Per-block summary over raw values.
        //
        // This can be replaced depending on the query:
        // - frequency map: count values equal to x
        // - sorted array/list: count values <= x, >= x, or inside a value range
        // - min/max: block extrema
        // - bitset: presence or distinct-value information over a small domain
        //
        // A full-block add changes only lazy[b], because every value shifts equally.
        Map<Long, Integer>[] freq;

        public SqrtFreq(int[] arr) {
            n = arr.length;
            B = (int) Math.sqrt(n) + 1;
            m = (n + B - 1) / B;

            a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = arr[i];
            }

            lazy = new long[m];
            freq = new HashMap[m];

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

        private void rebuild(int b) {
            // Rebuild the block summary after raw values in this block change.
            freq[b] = new HashMap<>();

            for (int i = blockL(b); i <= blockR(b); i++) {
                freq[b].put(a[i], freq[b].getOrDefault(a[i], 0) + 1);
            }
        }

        private void push(int b) {
            // Materialize the block's lazy shift into its raw values.
            if (lazy[b] == 0) {
                return;
            }

            long delta = lazy[b];

            for (int i = blockL(b); i <= blockR(b); i++) {
                a[i] += delta;
            }

            lazy[b] = 0;
            rebuild(b);
        }

        public void add(int ql, int qr, long delta) {
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

            // Full blocks shift uniformly, so their raw-value summaries remain valid.
            for (int b = lb + 1; b < rb; b++) {
                lazy[b] += delta;
            }
        }

        public int count(long target) {
            // Count target over the entire array.
            int res = 0;

            for (int b = 0; b < m; b++) {
                // Actual value = raw value + lazy[b].
                // Therefore raw value must equal target - lazy[b].
                long need = target - lazy[b];
                res += freq[b].getOrDefault(need, 0);
            }

            return res;
        }

        public int count(int ql, int qr, long target) {
            // Count target inside [ql, qr].
            int lb = ql / B;
            int rb = qr / B;
            int res = 0;

            if (lb == rb) {
                for (int i = ql; i <= qr; i++) {
                    if (a[i] + lazy[lb] == target) {
                        res++;
                    }
                }
                return res;
            }

            for (int i = ql; i <= blockR(lb); i++) {
                if (a[i] + lazy[lb] == target) {
                    res++;
                }
            }

            for (int i = blockL(rb); i <= qr; i++) {
                if (a[i] + lazy[rb] == target) {
                    res++;
                }
            }

            for (int b = lb + 1; b < rb; b++) {
                long need = target - lazy[b];
                res += freq[b].getOrDefault(need, 0);
            }

            return res;
        }

        public long get(int i) {
            int b = i / B;
            return a[i] + lazy[b];
        }
    }


    //----------------------------- not as useful since this is more like a seg tree, but kept for illustration


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
