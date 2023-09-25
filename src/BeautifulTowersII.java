import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BeautifulTowersII {

    public long maximumSumOfHeights(List<Integer> a) {
        int n = a.size();
        Deque<Integer> dq = new ArrayDeque<>();
        long[] rv = new long[n];
        for (int i = n - 1; i >= 0; --i) {
            while (!dq.isEmpty() && a.get(dq.peek()) >= a.get(i)) {
                dq.pop();
            }
            int ri = n;
            if (!dq.isEmpty()) {
                ri = dq.peek();
            }
            long crv = (0L + ri - i) * a.get(i);
            if (ri < n) {
                crv += rv[ri];
            }
            //  System.out.println("i="+i+" ri="+ri+" crv="+crv);
            rv[i] = crv;
            dq.push(i);
        }
        dq.clear();
        long[] lv = new long[n];
        long res = 0;
        for (int i = 0; i < n; ++i) {
            while (!dq.isEmpty() && a.get(dq.peek()) >= a.get(i)) {
                dq.pop();
            }
            int li = -1;
            if (!dq.isEmpty()) {
                li = dq.peek();
            }
            long clv = (0L + i - li) * a.get(i);
            if (li >= 0) {
                clv += lv[li];
            }
            lv[i] = clv;
            long cur = clv + rv[i] - a.get(i);
            res = Math.max(res, cur);
            dq.push(i);
        }
        return res;
    }
}
