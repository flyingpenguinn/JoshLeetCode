import java.util.*;

public class MergeAdjacentEqualElement {
    // must be a while loop for stack pop
    public List<Long> mergeAdjacent(int[] a) {
        int n = a.length;
        Deque<Long> st = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            if (st.isEmpty()) {
                st.push(v);
            } else {
                while (!st.isEmpty()) {
                    long top = st.peek();
                    if (top == v) {
                        st.pop();
                        v *= 2;
                    } else {

                        break;
                    }
                }
                st.push(v);
            }
        }
        List<Long> res = new ArrayList<>();
        while (!st.isEmpty()) {
            res.add(st.pop());
        }
        Collections.reverse(res);
        return res;
    }
}
