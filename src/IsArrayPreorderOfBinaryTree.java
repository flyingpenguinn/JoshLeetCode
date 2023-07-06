import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class IsArrayPreorderOfBinaryTree {
    public boolean isPreorder(List<List<Integer>> a) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.size();
        st.push(a.get(0).get(0));
        for (int i = 1; i < n; ++i) {
            while (!st.isEmpty() && !st.peek().equals(a.get(i).get(1))) {

                st.pop();
            }
            if (st.isEmpty()) {
                return false;
            }
            st.push(a.get(i).get(0));
        }
        return true;
    }
}
