import java.util.ArrayList;
import java.util.List;

public class CountShadowPairsI {
    public long shadowPairs(int[] a) {
        int n = a.length;
        List<Integer> st = new ArrayList<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            while (!st.isEmpty() && st.get(st.size() - 1) > v) {
                st.remove(st.size() - 1);
            }
            int pos = binary(st, v);
            long cur = pos + 1;
            res += cur;
            st.add(v);
        }
        return res;
    }

    private int binary(List<Integer> a, int v) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) < v) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
