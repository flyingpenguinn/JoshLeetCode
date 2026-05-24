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
}
