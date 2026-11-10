import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MinAdjacentSwapsToReachKth {
    // get the kth permutation, then relabel each digit by its k-th occurrence in the original string
    // min adjacent swaps = number of inversions of the relabeled array
    static class FenWick {
        private int[] bit;

        public FenWick(int n) {
            this.bit = new int[n];
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

    public int getMinSwaps(String s, int k) {
        int n = s.length();
        char[] c = s.toCharArray();
        while (k > 0) {
            nextPerm(c);
            --k;
        }
        Map<Integer, Deque<Integer>> tm = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - '0';
            tm.computeIfAbsent(cind, p -> new ArrayDeque<>()).offerLast(i + 1);
        }
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            int sind = c[i] - '0';
            int cind = tm.get(sind).pollFirst();
            a[i] = cind;
        }
        FenWick fen = new FenWick(n + 1);
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int smaller = fen.q(v);
            int bigger = i - smaller;
            res += bigger;
            fen.u(v, 1);
        }
        return res;
    }

    private void nextPerm(char[] c) {
        int n = c.length;
        int i = n - 1;
        while (i - 1 >= 0 && c[i] <= c[i - 1]) {
            --i;
        }
        if (i == 0) {
            return;
        }
        int pos = i - 1;
        int j = i;
        int last = -1;
        while (j < n && c[j] > c[pos]) {
            last = j;
            ++j;
        }
        swap(c, pos, last);
        reverse(c, pos + 1, n - 1);
    }

    private void reverse(char[] c, int i, int j) {
        while (i < j) {
            swap(c, i, j);
            ++i;
            --j;
        }
    }

    private void swap(char[] c, int i, int j) {
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
