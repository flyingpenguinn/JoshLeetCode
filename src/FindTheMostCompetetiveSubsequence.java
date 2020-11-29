import java.util.ArrayDeque;
import java.util.Deque;

public class FindTheMostCompetetiveSubsequence {
    // use stack and mind the k length restriction!
    public int[] mostCompetitive(int[] a, int k) {
        int n = a.length;
        //     System.out.println(n);
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int rem = n - i;
            if (st.size() + rem == k) {
                st.offerLast(a[i]);
            } else {
                while (!st.isEmpty() && a[i] < st.peekLast() && st.size() + rem > k) {
                    // mind this
                    st.pollLast();
                }
                st.offerLast(a[i]);
            }
        }
        //   System.out.println(st);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = st.pollFirst();
        }
        return res;
    }
}
