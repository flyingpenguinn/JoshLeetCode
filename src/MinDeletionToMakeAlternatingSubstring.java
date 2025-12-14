import java.util.ArrayList;
import java.util.List;

public class MinDeletionToMakeAlternatingSubstring {
    // to delete to make alternating = len - changing i
    private int[] bit;

    private void u(int i, int d) {
        while (i < bit.length) {
            bit[i] += d;
            i += i & (-i);
        }
    }

    private int q(int i) {
        int cur = 0;
        while (i > 0) {
            cur += bit[i];
            i -= i & (-i);
        }
        return cur;
    }


    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        char[] a = s.toCharArray();
        bit = new int[n];

        for (int i = 1; i < n; i++) {
            if (a[i] != a[i - 1]) {
                u(i, 1);
            }
        }

        int qn = queries.length;
        List<Integer> res = new ArrayList<>();


        for (int i = 0; i < qn; i++) {
            if (queries[i][0] == 1) {
                int cur = queries[i][1];
                if (cur > 0) {
                    flip(a, cur);
                    int nv1 = calcv(a, cur, cur - 1);
                    flip(a, cur);
                    int ov1 = calcv(a, cur, cur - 1);
                    u(cur, nv1 - ov1);
                }
                if (cur + 1 < n) {
                    flip(a, cur);
                    int nv2 = calcv(a, cur, cur + 1);
                    flip(a, cur);
                    int ov2 = calcv(a, cur, cur + 1);
                    u(cur + 1, nv2 - ov2);
                }
                flip(a, cur);

            } else {
                int l = queries[i][1];
                int r = queries[i][2];
                if (l == r) {
                    res.add(0);
                } else {
                    int t = q(r) - q(l);
                    res.add((r - l) - t);
                }
            }
        }

        int[] rres = new int[res.size()];
        for (int i = 0; i < rres.length; i++) {
            rres[i] = res.get(i);
        }
        return rres;
    }

    protected int calcv(char[] a, int cur, int other) {
        if (a[cur] != a[other]) {
            return 1;
        }
        return 0;
    }

    protected void flip(char[] a, int cur) {
        if (a[cur] == 'A') {
            a[cur] = 'B';
        } else {
            a[cur] = 'A';
        }
    }
}
