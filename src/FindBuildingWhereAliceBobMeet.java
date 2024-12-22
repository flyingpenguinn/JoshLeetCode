import base.ArrayUtils;

import java.util.*;

public class FindBuildingWhereAliceBobMeet {
    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        int n = a.length;
        int qn = qs.length;
        int[][] nqs = new int[qn][3];
        for (int i = 0; i < qn; ++i) {
            nqs[i][0] = Math.min(qs[i][0], qs[i][1]);
            nqs[i][1] = Math.max(qs[i][0], qs[i][1]);
            nqs[i][2] = i;
        }
        Arrays.sort(nqs, (x, y) -> Integer.compare(x[1], y[1]));
        int[] res = new int[qn];
        Arrays.fill(res, -1);
        List<Integer> st = new ArrayList<>();
        int qi = qn - 1;
        for (int i = n - 1; i >= 0; --i) {

            while (!st.isEmpty() && a[st.get(st.size() - 1)] <= a[i]) {
                st.remove(st.size() - 1);
            }
            st.add(i);
            while (qi >= 0 && i == nqs[qi][1]) {
                int x = Math.min(nqs[qi][0], nqs[qi][1]);
                int y = Math.max(nqs[qi][0], nqs[qi][1]);
                int idx = nqs[qi][2];
                if (x == y) {
                    res[idx] = x;
                } else if (a[x] < a[y]) {
                    res[idx] = y;
                } else {
                    int pos = binary(a, st, a[x]);
                    if (pos >= 0 && pos < st.size()) {
                        res[idx] = st.get(pos);
                    }
                }

                --qi;
            }
        }
        return res;
    }

    private int binary(int[] a, List<Integer> st, int t) {
        int l = 0;
        int u = st.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[st.get(mid)] > t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindBuildingWhereAliceBobMeet().leftmostBuildingQueries(ArrayUtils.read1d("[1,2,1,2,1,2]"), ArrayUtils.read("[[0,2]]"))));
    }

}
